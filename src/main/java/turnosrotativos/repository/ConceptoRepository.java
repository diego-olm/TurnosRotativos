package turnosrotativos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import turnosrotativos.entities.Concepto;

public interface ConceptoRepository extends JpaRepository<Concepto,Integer> {

}
