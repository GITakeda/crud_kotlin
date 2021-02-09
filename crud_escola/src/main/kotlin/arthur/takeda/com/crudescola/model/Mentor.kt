package arthur.takeda.com.crudescola.model

import javax.persistence.*

@Entity
data class Mentor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var nome: String? = null,

    @Column(columnDefinition = "BIT NOT NULL")
    var active: Boolean = true
)