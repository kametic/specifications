/**
 * Copyright (C) 2013 Kametic <epo.jemba@kametic.com>
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
 * or any later version
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kametic.specifications;

/**
 * A specification expecting that at least one of the given specification is satisfied.
 * @param <T>
 */
public class OrSpecification<T> extends CompositeSpecification<T> {

    /**
     * Construct a specification expecting that at least one of the given specification is satisfied.
     *
     * @param specificationParticipants left hand specification
     */
    public OrSpecification(Specification<? super T>... specificationParticipants) {
        super(specificationParticipants);
    }

    @Override
    public boolean isSatisfiedBy(T candidate) {
        boolean result = false;
        
        for(Specification<? super T> participant : this.childSpecifications)
        {
            result |= participant.isSatisfiedBy(candidate);
        }
        
        return result;
    }

}