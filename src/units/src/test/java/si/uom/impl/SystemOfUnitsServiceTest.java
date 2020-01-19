/*
 *  Unit-API - Units of Measurement API for Java
 *  Copyright (c) 2005-2015, Jean-Marie Dautelle, Werner Keil, V2COM.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of JSR-363 nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package si.uom.impl;

import static org.junit.Assert.*;

import java.util.Collection;

import javax.measure.spi.ServiceProvider;
import javax.measure.spi.SystemOfUnits;
import javax.measure.spi.SystemOfUnitsService;

import org.junit.BeforeClass;
import org.junit.Test;

public class SystemOfUnitsServiceTest {
    private static final String SI_NAME = "SI";
    private static final String NONSI_NAME = "Non-SI Units";
    
    private static final int UNITS_EXPECTED = 21;
    private static SystemOfUnitsService defaultService;

    @BeforeClass
    public static void setUp() {
	defaultService = ServiceProvider.current().getSystemOfUnitsService();
    }

    @Test
    public void testDefaultUnitSystemService() {
	assertNotNull(defaultService);
	SystemOfUnits system = defaultService.getSystemOfUnits();
	assertNotNull(system);
	assertEquals("si.uom.SI", system.getClass().getName());
	assertEquals("SI", system.getName());
	assertNotNull(system.getUnits());
	assertEquals(UNITS_EXPECTED, system.getUnits().size()); 
	// SI extends Units, this is only its additional collection
    }

    @Test
    public void testOtherUnitSystems() {
	Collection<SystemOfUnits> systems = defaultService.getAvailableSystemsOfUnits();
	assertNotNull(systems);
	assertEquals(2, systems.size()); // we'd expect SI and NonSI here

	for (SystemOfUnits s : systems) {
	    checkSystem(s);
	}
    }

    @Test
    public void testOtherProviders() {
	ServiceProvider otherProvider = ServiceProvider.available().get(1);
	SystemOfUnitsService otherService = otherProvider.getSystemOfUnitsService();
	assertNotNull(otherService);
	assertNotNull(otherService.getSystemOfUnits());
	assertEquals("Units", otherService.getSystemOfUnits().getName());
    }

    private void checkSystem(SystemOfUnits system) {
	assertNotNull(system);
	assertTrue(SI_NAME.equals(system.getName()) || NONSI_NAME.equals(system.getName()));
	if (SI_NAME.equals(system.getName())) {
	    assertEquals("si.uom.SI", system.getClass().getName());
	    assertEquals(UNITS_EXPECTED, system.getUnits().size());
	} else if (NONSI_NAME.equals(system.getName())) {
	    assertEquals("si.uom.NonSI", system.getClass().getName());
	    assertEquals(35, system.getUnits().size());
	}
    }
}
