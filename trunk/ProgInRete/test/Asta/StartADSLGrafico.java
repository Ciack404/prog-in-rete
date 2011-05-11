package Asta;

/**
 * @(#)StartADSLGrafico.java
 *
 *
 * @author Capello Matteo, Bolognesi Massimo
 * @version 1.00 2009/3/4
 */

import JAM.*;

import java.util.*;
import java.rmi.*;
import java.net.*;

public class StartADSLGrafico {
	public static void main(String[] args) {
		try{
			ADSLObservable obs=new ADSLObservable();
			ADSLImpl adsl=new ADSLImpl(obs);
			new ADSLObserver (adsl, obs);
		}
		catch (Exception e) {
			System.err.println("Bind fallito!");
		}
	}
}
