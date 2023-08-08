import React from 'react';
import { Form, Button, Row, Col } from 'react-bootstrap';
import './TeacherForm.scss'

function TeacherForm() {
  return (
    <Form>
        <Row>
            <Col md={4}>
            <Form.Group controlId="imagen1">
                <Form.Control type="file" accept="image/*" />
            </Form.Group>
            </Col>
            <Col md={4}>
            <Form.Group controlId="imagen2">
                <Form.Control type="file" accept="image/*" />
            </Form.Group>
            </Col>
            <Col md={4}>
            <Form.Group controlId="imagen3">
                <Form.Control type="file" accept="image/*" />
            </Form.Group>
            </Col>
            <Col md={4}>
            <Form.Group controlId="imagen4">
                <Form.Control type="file" accept="image/*" />
            </Form.Group>
            </Col>
            <Col md={4}>
            <Form.Group controlId="imagen4">
                <Form.Control type="file" accept="image/*" />
            </Form.Group>
            </Col>
        </Row>
        <Row>
            <Col md={6}>
                <Form.Group controlId="nombre">
                <Form.Label>Nombre</Form.Label>
                <Form.Control type="text" placeholder="Ingresa tu nombre" />
                </Form.Group>
            </Col>
            <Col md={6}>
                <Form.Group controlId="apellido">
                <Form.Label>Apellido</Form.Label>
                <Form.Control type="text" placeholder="Ingresa tu apellido" />
                </Form.Group>
            </Col>
        </Row>

        <Row>
            <Col md={6}>
                <Form.Group controlId="descripcion">
                <Form.Label>Descripción</Form.Label>
                <Form.Control as="textarea" placeholder="Ingresa una descripción" rows={3} />
                </Form.Group>
            </Col>
            <Col md={6}>
                <Form.Group controlId="pais">
                <Form.Label>País</Form.Label>
                <Form.Control type="text" placeholder="Ingresa tu país" />
                </Form.Group>
            </Col>
        </Row>

        <Row>
            <Col md={6}>
                <Form.Group controlId="provincia">
                <Form.Label>Provincia</Form.Label>
                <Form.Control type="text" placeholder="Ingresa tu provincia" />
                </Form.Group>
            </Col>
            <Col md={6}>
                <Form.Group controlId="correo">
                <Form.Label>Correo</Form.Label>
                <Form.Control type="email" placeholder="Ingresa tu correo electrónico" />
                </Form.Group>
            </Col>
        </Row>

        <Row>
            <Col md={6}>
                <Form.Group controlId="telefono">
                <Form.Label>Teléfono</Form.Label>
                <Form.Control type="tel" placeholder="Ingresa tu número de teléfono" />
                </Form.Group>
            </Col>
            <Col md={6}>
                <Form.Group controlId="nombreCalle">
                <Form.Label>Nombre de Calle</Form.Label>
                <Form.Control type="text" placeholder="Ingresa el nombre de la calle" />
                </Form.Group>
            </Col>
        </Row>

        <Row>
            <Col md={6}>
                <Form.Group controlId="numeroCalle">
                <Form.Label>Número de Calle</Form.Label>
                <Form.Control type="text" placeholder="Ingresa el número de la calle" />
                </Form.Group>
            </Col>
            <Col md={6}>
                <Form.Group controlId="especialidad">
                <Form.Label>Especialidad</Form.Label>
                <Form.Control type="text" placeholder="Ingresa tu especialidad" />
                </Form.Group>
            </Col>
        </Row>

        <Row>
            <Col md={6}>
                <Form.Group controlId="nivelEspecialidad">
                <Form.Label>Nivel de Especialidad</Form.Label>
                <Form.Control type="text" placeholder="Ingresa el nivel de especialidad" />
                </Form.Group>
            </Col>
            <Col md={6}>
                <Form.Group controlId="modalidades">
                <Form.Label>Modalidades</Form.Label>
                <Form.Control type="text" placeholder="Ingresa las modalidades que ofreces" />
                </Form.Group>
            </Col>
        </Row>

        <Row>
            <Col md={6}>
                <Form.Group controlId="inicioClase">
                <Form.Label>Inicio de Clase</Form.Label>
                <Form.Control type="time" />
                </Form.Group>
            </Col>
            <Col md={6}>
                <Form.Group controlId="finClase">
                <Form.Label>Fin de Clase</Form.Label>
                <Form.Control type="time" />
                </Form.Group>
            </Col>
        </Row>

        <Row>
            <Col md={6}>
                <Form.Group controlId="disponibilidad">
                <Form.Label>Disponibilidad</Form.Label>
                <Form.Control type="text" placeholder="Ingresa tu disponibilidad" />
                </Form.Group>
            </Col>
            <Col md={6}>
                <Button variant="primary" type="submit">
                Enviar
                </Button>
            </Col>
        </Row>
  </Form>
  );
}

export default TeacherForm;