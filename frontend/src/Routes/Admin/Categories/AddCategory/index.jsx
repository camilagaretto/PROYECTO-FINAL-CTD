import React from 'react'
import Container from 'react-bootstrap/Container';
import CategoryForm from '../../../../Components/Admin/CategoryForm'

const AddCategory = () => {

  return (
    <main>
      <Container>
        <section className='dashboard__container'>
          <CategoryForm />
        </section>
      </Container>
    </main>
  )
}

export default AddCategory