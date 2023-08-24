import React from 'react'
import Container from 'react-bootstrap/Container';
import CharacteristicForm from '../../../../Components/Admin/CharasteristicsForm'

const AddCharacteristic = () => {

  return (
    <main>
      <Container>
        <section className='dashboard__container'>
          <CharacteristicForm />
        </section>
      </Container>
    </main>
  )
}

export default AddCharacteristic