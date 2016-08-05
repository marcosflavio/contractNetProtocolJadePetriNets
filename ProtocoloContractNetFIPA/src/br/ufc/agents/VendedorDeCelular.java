package br.ufc.agents;

import br.ufc.behaviours.ResponderBehaviour;
import br.ufc.common.Operacoes;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class VendedorDeCelular extends Agent {

	/**
	 * @author marcosf
	 */
	private static final long serialVersionUID = 1L;

	private Operacoes ope;
	private ResponderBehaviour respB;
	@Override
	protected void setup() {
		
		
		System.out.println(this.getLocalName()+" esperando alguma proposta...");
		
		MessageTemplate template = MessageTemplate.and(
				MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET),
				MessageTemplate.MatchPerformative(ACLMessage.CFP));
		
		
		ope = new Operacoes();
		// Descrição do serviço
		ServiceDescription servico = new ServiceDescription();
		// Seu serviço eh vender celular
		servico.setType("venda celular");
		servico.setName(this.getLocalName());
		ope.registraServico(servico, this);
		ope.recebeMensagens("celular", "Olá, temos diversos celulares para a venda!", this,template);
	}

}
