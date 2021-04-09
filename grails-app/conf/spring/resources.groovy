import org.springframework.beans.factory.config.MethodInvokingFactoryBean

// Place your Spring DSL code here
beans = {
    System.setProperty('org.hibernate.envers.audit_table_prefix', 'AUDITED_')
    System.setProperty('org.hibernate.envers.audit_table_suffix', '')
}