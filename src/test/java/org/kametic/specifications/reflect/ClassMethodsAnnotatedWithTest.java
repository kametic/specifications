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
package org.kametic.specifications.reflect;

import static org.fest.assertions.Assertions.assertThat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;
import org.kametic.specifications.reflect.ClassMethodsAnnotatedWith;

public class ClassMethodsAnnotatedWithTest {

	private ClassMethodsAnnotatedWith underTest;


	@Retention(RetentionPolicy.RUNTIME)
	static @interface MyAnno
	{
		
	}
	static interface Z1 {}
	static class A1 {
		@MyAnno
		void inita1 () {}
	}
	
	static class B1 extends A1 implements Z1{
		void initb1 () {}
	}
	
	static class C1 extends B1{
		void initc1 () {}
	}

	////////////////////////////
	
	static interface A2 {
		@MyAnno
		void inita ();
	}
	
	static interface B2 extends A2 {
		void initb ();
	}
	
	static class C2 implements B2{
		public void initc () {}

		@Override
		public void inita() {}

		@Override
		public void initb() {}
	}
	
	
	@Before
	public void init ()
	{
		underTest = new ClassMethodsAnnotatedWith(MyAnno.class);
	}
	
	@Test
	public void ancestor_find_should_work_properly ()
	{
		assertThat(underTest.getAllInterfacesAndClasses(C2.class)).containsOnly(A2.class , B2.class , C2.class) ;
		
		assertThat(underTest.getAllInterfacesAndClasses(C1.class)).containsOnly(A1.class , B1.class , C1.class , Z1.class);
	}
	
	
	@Test
	public void specification_should_work_fine () 
	{
		for (Class<?> itf : C2.class.getInterfaces())
		{
			System.out.println(itf);
		}
		System.out.println("");
		for (Method m : C2.class.getMethods())
		{
			
			System.out.println(m + " " + m.isAnnotationPresent(MyAnno.class));
		}
		
		assertThat( underTest.isSatisfiedBy(B2.class)).isTrue();
		assertThat( underTest.isSatisfiedBy(A2.class)).isTrue();
		assertThat( underTest.isSatisfiedBy(C2.class)).isTrue();
	}

}