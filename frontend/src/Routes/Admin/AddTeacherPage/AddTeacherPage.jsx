import React from 'react'
import Container from 'react-bootstrap/Container';
import TeacherForm from '../../../Components/TeacherForm/TeacherForm'
import './AddTeacherPage.scss'
import Banner from "../../../assets/teacher-form.svg";

const AddTeacherPage = () => {

  return (
    <main>
      <Container>
        <section className='dashboard__container'>
          <TeacherForm />
        </section>
      </Container>
    </main>
  )
}

export default AddTeacherPage