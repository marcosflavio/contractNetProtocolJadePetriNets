package br.ufc.common;

import br.ufc.behaviours.ResponderBehaviour;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Operacoes {

	public void recebeMensagens(String mensagem, String resp, Agent agente, MessageTemplate template){
		
		agente.addBehaviour(new CyclicBehaviour(agente) {
	
			private static final long serialVersionUID = 5104003219172219851L;

			@Override
			public void action() {

				//ACLMessage msg = agente.receive(); //problema aqui?

			//	if(msg != null){
			//		if(msg.getContent().equalsIgnoreCase(mensagem)){
						
						ResponderBehaviour beh = new ResponderBehaviour(this.myAgent, template);
						myAgent.addBehaviour(beh);
			//		}
			//	}else {
			//		block();
			//	}
				
			}
		});
		
	}
	
	public void registraServico(ServiceDescription sd, Agent agente){
		
		
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.addServices(sd);
		
		try{
			DFService.register(agente, dfd);
			
		}catch(FIPAException e){
			e.printStackTrace();
		}
		
	}
	
	
}
