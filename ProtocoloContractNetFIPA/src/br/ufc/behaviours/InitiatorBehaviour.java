package br.ufc.behaviours;

import java.util.Enumeration;
import java.util.Vector;

import javax.swing.plaf.synth.SynthSeparatorUI;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetInitiator;

public class InitiatorBehaviour extends ContractNetInitiator{

	public InitiatorBehaviour(Agent a, ACLMessage cfp) {
		super(a, cfp);
	}
	
	@Override
	public void handlePropose(ACLMessage propose, Vector v) {
		System.out.println("Agent "+propose.getSender().getName()+" proposed "+propose.getContent());
	}
	
	@Override
	public void handleRefuse(ACLMessage refuse) {
		System.out.println("Agent "+refuse.getSender().getName()+" refused");
	}
	
	@Override
	public void handleFailure(ACLMessage failure) {
		System.out.println("Entrei no handleFailure");
		
		if (failure.getSender().equals(myAgent.getAMS())) {
			// FAILURE notification from the JADE runtime: the receiver
			// does not exist
			System.out.println("Responder does not exist");
		}
		else {
			System.out.println("Agent "+failure.getSender().getName()+" failed");
		}
		// Immediate failure --> we will not receive a response from this agent
//		nResponders--;
	}
	
	
	@Override
	public void handleAllResponses(Vector responses, Vector acceptances) {
		
		/*if (responses.size() < nResponders) {
			// Some responder didn't reply within the specified timeout
			System.out.println("Timeout expired: missing "+(nResponders - responses.size())+" responses");
		}*/
		
		// Evaluate proposals.
		System.out.println("Entrei no AllResponses");
		int bestProposal = -1;
		AID bestProposer = null;
		ACLMessage accept = null;
		Enumeration e = responses.elements();
		
		System.out.println(responses.size());
		
		while (e.hasMoreElements()) {
			System.out.println("Entrei no while AllResponses");
			ACLMessage msg = (ACLMessage) e.nextElement();
			if (msg.getPerformative() == ACLMessage.PROPOSE) {
				ACLMessage reply = msg.createReply();
				reply.setPerformative(ACLMessage.REJECT_PROPOSAL);
				acceptances.addElement(reply);
				int proposal = Integer.parseInt(msg.getContent());
				if (proposal > bestProposal) {
					bestProposal = proposal;
					bestProposer = msg.getSender();
					accept = reply;
				}
			}
		}
		// Accept the proposal of the best proposer
		
		if (accept != null) {
			System.out.println("Accepting proposal "+bestProposal+" from responder "+bestProposer.getName());
			accept.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
		}						
	}
	
	@Override
	public void handleInform(ACLMessage inform) {
		
		System.out.println("Agent "+inform.getSender().getName()+" successfully performed the requested action");
	}

}
