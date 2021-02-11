package arthur.takeda.com.crudescola.model

import javax.persistence.*

@Entity
data class Mentoria (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @OneToOne
    @JoinColumn(name = "aluno_id")
    var aluno: Aluno? = null,

    @OneToOne
    @JoinColumn(name = "mentor_id")
    var mentor: Mentor? = null,

    @Column(columnDefinition = "BIT NOT NULL")
    var active: Boolean = true
)