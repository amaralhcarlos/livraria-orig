package br.com.caelum.livraria.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Venda;

@ManagedBean
@ViewScoped
public class VendasBean {

	public BarChartModel getVendasModel() {
		BarChartModel model = new BarChartModel();
		model.setAnimate(true);

		ChartSeries vendas2015 = new ChartSeries();
		vendas2015.setLabel("Vendas em 2015");

		ChartSeries vendas2016 = new ChartSeries();
		vendas2016.setLabel("Vendas em 2015");

		List<Venda> vendas = this.getVendas(1235);

		vendas.forEach(venda -> {
			vendas2015.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		});

		vendas = this.getVendas(1246);

		vendas.forEach(venda -> {
			vendas2016.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		});

		model.addSeries(vendas2015);
		model.addSeries(vendas2016);

		return model;
	}

	public List<Venda> getVendas(Integer seed) {

		List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();

		List<Venda> vendas = new ArrayList<>();

		Random random = new Random(seed);

		livros.forEach(livro -> {

			Integer quantidade = random.nextInt(100);

			vendas.add(new Venda(livro, quantidade));

		});

		return vendas;

	}

}
