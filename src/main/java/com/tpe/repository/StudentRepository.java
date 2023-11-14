package com.tpe.repository;

import com.tpe.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
        SORU : @Repository veya @Service yerine @Component kullanirsam ne olur ??

        CEVAP : @Repository ve @Service, ozellestirilmis @Component olarak dizayn edilmistir,
        bundaki amaclari soyle siralayabilirz :
	*	Kod okunabilirligi artirmak
	*	Ilgili katmana has exception mekanizmasinin devreye girmesini saglamak
	Mesela @Repository katmaninda bir uncheckedException olusursa ;
	   PersistenceExceptionTranslationPostProcessor = Spring Framework'ün bir parçası olarak
	   gelen ve özellikle veritabanı işlemleri sırasında ortaya çıkan istisnaları ele almayı
	   amaçlayan bir mekanizmadır. Bu mekanizma, unchecked exception türünden
	   istisnaları yakalar ve bu istisnaları Spring'in veritabanı işlemleri sırasında daha
	   spesifik bir şekilde ele alabileceği belirli istisnalar haline dönüştürür. ( unchecked
	   exception)  turunde exception firlamasina gore Spring kendini dizayn eder.

 */
@Repository //kod okunabilirliği
public interface StudentRepository extends JpaRepository<Student,Long> {




}
