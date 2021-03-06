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

import org.apache.commons.lang.ObjectUtils;


public class GreaterThanSpecification<T extends Comparable<T>> extends CompareToSpecification<T> implements SubsumableSpecification<T> {

    /**
     * Construct a new {@link GreaterThanSpecification}.
     *
     * @param value value that candidates need to exceed
     */
    public GreaterThanSpecification(T value) {
        super(value);
    }

    @Override
    protected boolean isSatisfyingComparison(int comparison) {
        return comparison > 0; // Positive integers are considered a greater than comparison
    }

    @Override
    public boolean subsumes(Specification<T> other) {
        // Other greater-than specifications are considered 'subsumed' whenever their value is equal or lower
        // Whenever a value is greater than, for example '2', it will always be greater than anything smaller
        return other instanceof GreaterThanSpecification<?> && ObjectUtils.compare(getValue(), ((GreaterThanSpecification<T>) other).getValue()) >= 0;
    }

}