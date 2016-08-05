package br.ufc.behaviours;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class SolicitanteB extends CyclicBehaviour {

	/**
	 * @author marcosf
	 */
	
	private static final long serialVersionUID = 1L;
	private Agent agente;
	
	public SolicitanteB(Agent agente) {
		this.agente = agente;

	}
	@Override
	public void action() {
		
		ACLMessage msg = agente.receive();
		
		if(msg != null){
			System.out.println(msg.getSender().getName() + " : " + msg.getContent());
			
		}else{
			block();
		}
	}

}
