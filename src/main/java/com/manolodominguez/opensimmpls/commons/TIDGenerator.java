/* 
 * Copyright (C) Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.manolodominguez.opensimmpls.commons;

/**
 * This class implements a ID generator that generates consecutive numeric IDs.
 *
 * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
 * @version 2.0
 */
public class TIDGenerator {

    /**
     * This method is the constructor of the class. It is create a new instance
     * of TIDGenerator.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @since 2.0
     */
    public TIDGenerator() {
        this.identifier = DEFAULT_ID;
    }

    /**
     * This method resets the ID generator to its initial internal value.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @since 2.0
     */
    public synchronized void reset() {
        this.identifier = DEFAULT_ID;
    }

    /**
     * This method generates a new ID.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @return an integer value that is unique.
     * @throws EIDGeneratorOverflow when the ID generator reaches its maximum
     * value.
     * @since 2.0
     */
    synchronized public int getNextIdentifier() throws EIDGeneratorOverflow {
        if (this.identifier >= Integer.MAX_VALUE) {
            throw new EIDGeneratorOverflow();
        } else {
            this.identifier++;
        }
        return (this.identifier);
    }

    /**
     * This method sets the ID generator internal value to the one specified as
     * an argument, only if the specified value is greater than the one the ID
     * generator has.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param newInternalIDValue the new internal ID value to set the ID
     * generator.
     * @since 2.0
     */
    synchronized public void setIdentifierIfGreater(int newInternalIDValue) {
        if (newInternalIDValue > this.identifier) {
            this.identifier = newInternalIDValue;
        }
    }

    /**
     * This method sets the ID generator new internal value.
     *
     * @author Manuel Domínguez Dorado - ingeniero@ManoloDominguez.com
     * @param newInternalIDValue the ID generator new internal value.
     * @since 2.0
     */
    synchronized public void setIdentifier(int newInternalIDValue) {
        this.identifier = newInternalIDValue;
    }

    private int identifier;
    
    private static final int DEFAULT_ID = 0;
}
