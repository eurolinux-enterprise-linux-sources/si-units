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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.measure.spi.SystemOfUnits;
import javax.measure.spi.SystemOfUnitsService;

import si.uom.NonSI;
import si.uom.SI;

/**
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 0.5, November 5, 2016
 */
//@Priority(20)
class SISystemService implements SystemOfUnitsService {

    final Map<String, SystemOfUnits> souMap = new HashMap<>();

    SISystemService() {
	// souMap.put(Units.class.getSimpleName(), Units.getInstance());
	souMap.put(SI.getInstance().getName(), SI.getInstance());
	souMap.put(NonSI.getInstance().getName(), NonSI.getInstance());
    }

    public Collection<SystemOfUnits> getAvailableSystemsOfUnits() {
	return souMap.values();
    }

    @Override
    public SystemOfUnits getSystemOfUnits() {
	return getSystemOfUnits(SI.getInstance().getName());
    }

    @Override
    public SystemOfUnits getSystemOfUnits(String name) {
	return souMap.get(name);
    }
}
