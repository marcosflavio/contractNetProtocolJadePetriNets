package br.ufc.behaviours;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import clojure.lang.Obj;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class BuscaB extends TickerBehaviour{

	/**
	 * @author marcosf
	 */
	private static final long serialVersionUID = 1L;
	private ServiceDescription sd;
	private String pedido;
	
	public void setPedido(String pedido){
		this.pedido = pedido;
	}
	
	
	public void setService(ServiceDescription sd){
		this.sd = sd;
	}
	public BuscaB(Agent a, long period) {
		super(a, period);
	}

	@Override
	protected void onTick() {	
		
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.addServices(this.sd);
		try{
			
			DFAgentDescription [] resultado = DFService.search(myAgent, dfd);
		
			if(resultado.length != 0){
				
				ACLMessage msg = new ACLMessage(ACLMessage.CFP);
				
		  		for (int i = 0; i < resultado.length; ++i) {
		  			System.out.println(resultado[i].getName().getLocalName() + " aqui");
		  			msg.addReceiver(new AID(resultado[i].getName().getLocalName(), AID.ISLOCALNAME));
		  		}
		  		
					msg.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
					// We want to receive a reply in 10 secs
					msg.setReplyByDate(new Date(System.currentTimeMillis() + 10000));
					msg.setContent(this.pedido);
				
				msg.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
				// We want to receive a reply in 10 secs
				//msg.setReplyByDate(new Date(System.currentTimeMillis() + 10000));
				msg.setContent(this.pedido);
				myAgent.send(msg);
				InitiatorBehaviour init = new InitiatorBehaviour(myAgent, msg);
				myAgent.addBehaviour(init);
				
				stop(); //mata o comportamento
				
			}
			
		}catch(FIPAException e){
			e.printStackTrace();
		}
		
	}
	

	
}
