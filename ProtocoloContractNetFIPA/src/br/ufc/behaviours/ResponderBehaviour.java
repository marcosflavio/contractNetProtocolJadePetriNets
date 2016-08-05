package br.ufc.behaviours;

import jade.core.Agent;
import jade.core.behaviours.DataStore;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.ContractNetResponder;

public class ResponderBehaviour extends ContractNetResponder {
	

	public ResponderBehaviour(Agent a, MessageTemplate mt) {
		super(a, mt);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ACLMessage handleCfp(ACLMessage cfp) throws NotUnderstoodException, RefuseException {
		
		System.out.println("Agent "+myAgent.getLocalName()+": CFP received from "+cfp.getSender().getName()+". Action is "+cfp.getContent());
		int proposal = evaluateAction();
		
		
		if (proposal > 2) {
			// We provide a proposal
			System.out.println("Agent "+myAgent.getLocalName()+": Proposing "+proposal);
			ACLMessage propose = cfp.createReply();
			propose.setPerformative(ACLMessage.PROPOSE);
			propose.setContent(String.valueOf(proposal));
			return propose;
		}
		else {
			// We refuse to provide a proposal
			System.out.println("Agent "+myAgent.getLocalName()+": Refuse");
			throw new RefuseException("evaluation-failed");
		}
	}

	@Override
	public ACLMessage handleAcceptProposal(ACLMessage cfp, ACLMessage propose,ACLMessage accept) throws FailureException {
		System.out.println("Agent "+myAgent.getLocalName()+": Proposal accepted");
		if (performAction()) {
			System.out.println("Agent "+myAgent.getLocalName()+": Action successfully performed");
			ACLMessage inform = accept.createReply();
			inform.setPerformative(ACLMessage.INFORM);
			return inform;
		}
		else {
			System.out.println("Agent "+myAgent.getLocalName()+": Action execution failed");
			throw new FailureException("unexpected-error");
		}	
	}
	@Override
	public void handleRejectProposal(ACLMessage cfp, ACLMessage propose, ACLMessage reject) {
		System.out.println("Agent "+myAgent.getLocalName()+": Proposal rejected");
	}
	
	private int evaluateAction() {
		// Simulate an evaluation by generating a random number
		return (int) (Math.random() * 10);
	}

	private boolean performAction() {
		// Simulate action execution by generating a random number
		return (Math.random() > 0.2);
	}
	
}
