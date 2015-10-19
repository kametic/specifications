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
 * Helper class to easily create specifications.
 */
public class Specifications {

    /**
     * Returns a specification expecting that the two given specifications are satisfied.
     *
     * @param <T> the specification type
     * @param lhs the left hand specification
     * @param rhs the right hand specification
     * @return an AndSpecification
     */
    public static <T> Specification<T> and(Specification<T> lhs, Specification<? super T> rhs) {
        return new AndSpecification<T>(lhs, rhs);
    }

    /**
     * Returns a specification expecting that at least one of the two specifications is satisfied.
     *
     * @param <T> the specification type
     * @param lhs the left hand specification
     * @param rhs the right hand specification
     * @return an OrSpecification
     */
    public static <T> Specification<T> or(Specification<T> lhs, Specification<? super T> rhs) {
        return new OrSpecification<T>(lhs, rhs);
    }

    /**
     * Returns a specification expecting that the given specification is not satisfied. 
     *
     * @param <T> the specification type
     * @param proposition the specification
     * @return a NotSpecification
     */
    public static <T> Specification<T> not(Specification<T> proposition) {
        return new NotSpecification<T>(proposition);
    }

    /**
     * Construct a new equals specification.
     *
     * @param <T> type of the candidates being matched
     * @param value the value that our property should be equal to
     * @return new equals specification
     */
    public static <T> Specification<T> equalTo(Object value) {
        return new EqualToSpecification<T>(value);
    }

    public static <T extends Comparable<T>> Specification<T> greaterThan(T value) {
        return new GreaterThanSpecification<T>(value);
    }

    public static <T extends Comparable<T>> Specification<T> greaterOrEqualTo(T value) {
        return new OrSpecification<T>(equalTo(value), greaterThan(value));
    }

    public static <T extends Comparable<T>> Specification<T> lessThan(T value) {
        return new LessThanSpecification<T>(value);
    }

    public static <T extends Comparable<T>> Specification<T> lessOrEqualTo(T value) {
        return new OrSpecification<T>(equalTo(value), lessThan(value));
    }

}
