package com.spring.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class RenderizadorPaginas<T>{
	private String url;
	private Page<T>page;
	private int totalPags;
	private int nElemPorPag;
	private int pagActual;
	private List<ElementosPagina> paginas;
	
	public RenderizadorPaginas(String url, Page<T> page) {
		
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<ElementosPagina>();
		
		totalPags = page.getTotalPages();
		nElemPorPag = page.getSize();
		pagActual = page.getNumber() + 1;
		
		int desde, hasta;
		desde = 1;
		hasta = totalPags;
		
		if(totalPags < nElemPorPag) {
			desde = 1;
			hasta = totalPags;
			
		}

		else {
			if(pagActual <= totalPags/2) {
				desde = 1;
				hasta = nElemPorPag;
				
			}
			//Página final
			else if(pagActual >= totalPags - nElemPorPag) {
				desde = totalPags - nElemPorPag + 1;
				hasta = nElemPorPag;
			}
			//En el medio
			else {
				desde = pagActual - nElemPorPag/2;
				hasta = nElemPorPag;
			}
		}
		
		
		for(int i=0; i<hasta; i++) {
			paginas.add(new ElementosPagina(desde + i, pagActual == desde+i));
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTotalPags() {
		return totalPags;
	}

	public void setTotalPags(int totalPags) {
		this.totalPags = totalPags;
	}

	public int getPagActual() {
		return pagActual;
	}

	public void setPagActual(int pagActual) {
		this.pagActual = pagActual;
	}

	public List<ElementosPagina> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<ElementosPagina> paginas) {
		this.paginas = paginas;
	}
	

	public boolean isFirst() {
		return page.isFirst();
	}
	public boolean isLast() {
		return page.isLast();
	}
	public boolean isHasNext() {
		return page.hasNext();
	}
	public boolean isHasPrevious() {
		return page.hasPrevious();
	}
}
