package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.Pais;
import service.PaisService;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaisTest {
	Pais pais, copia;
	static int id = 0;
	double delta = 0.01;

	@Before
	public void setUp() throws Exception {
		System.out.println("Setup");
		pais = new Pais(2, "Bielorussia", 9491800, 207600.0);
		copia = new Pais(2, "Bielorussia", 9491800, 207600.0);
		System.out.println(pais);
		System.out.println(copia);
	}

	@Test
	public void test00Carregar() {
		System.out.println("Carregar");
		Pais fixture = new Pais(1, "Brasil", 210147125, 8515767.0);
		Pais novo = new Pais(1, null, 0, 0);
		PaisService service = new PaisService();
		novo = service.carregar(novo.getId());
		assertEquals(novo, fixture);
	}

	@Test
	public void test01Criar() {
		System.out.println("Criar");
		PaisService service = new PaisService();
		service.criar(pais);
		id = pais.getId();
		System.out.println(id);
		copia.setId(id);
		assertEquals(pais, copia);
	}

	@Test
	public void test02Atualizar() {
		System.out.println("atualizar");
		pais.setArea(555);
		copia.setArea(555);
		PaisService service = new PaisService();
		service.atualizar(pais);
		pais = service.carregar(pais.getId());
		assertEquals(pais, copia);
	}

	@Test
	public void test03Excluir() {
		System.out.println("Excluir");
		Pais vazio = new Pais();
		PaisService service = new PaisService();
		service.excluir(pais);
		pais = service.carregar(pais.getId());
		assertEquals(pais, vazio);
	}

	@Test
	public void test05MaiorPais() {
		PaisService service = new PaisService();
		service.criar(pais);
		Pais novo = new Pais(3, "Canada", 37242571, 9984670);
		service.criar(novo);
		Pais maior = new Pais();

		maior = service.maiorPais(maior);
		assertEquals(maior.getNome(), "Brasil");
		assertEquals(maior.getPopulacao(), 210147125);
	}

	@Test
	public void test06TresPaises() {
		PaisService service = new PaisService();
		Pais[] paises = new Pais[3];
		for(int i = 0; i < 3; i++) {
			paises[i] = new Pais();
		}
		paises = service.tresPaises(paises);
		assertEquals(1, paises[0].getId());
		assertEquals("Brasil", paises[0].getNome());
		assertEquals(2, paises[1].getId());
		assertEquals("Bielorussia", paises[1].getNome());
		assertEquals(3, paises[2].getId());
		assertEquals("Canada", paises[2].getNome());

	}

	@Test
	public void test07MenorArea() {
		PaisService service = new PaisService();
		Pais menor = new Pais();
		menor = service.menorArea(menor);
		assertEquals(menor.getNome(), "Bielorussia");
		assertEquals(menor.getArea(), 207600, delta);
	}

}