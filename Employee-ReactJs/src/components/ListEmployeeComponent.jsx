import React, { useEffect, useState } from 'react';
import { deleteEmployee, listEmployees } from '../services/EmployeeService';
import { useNavigate } from 'react-router-dom';


const ListEmployeeComponent = () => {

    const [employees, setEmployees] = useState([]);
    const navigator = useNavigate();

    useEffect(() => {
        getAllEmployees();
    }, []);

    function getAllEmployees() {
        listEmployees().then((response) => {
            setEmployees(response.data);
        }).catch(error => {
            console.error(error);
        });
    }

    const addNewEmployee = () => {
        navigator("/add-employee");
    }

    const updateEmployee = (id) => {
        navigator(`/update-employee/${id}`);
    }

    const removeEmployee = (id) => {
        deleteEmployee(id).then(() => {
            getAllEmployees();
        }).catch(error => {
            console.error(error);
        })
    }

    return (

        <div className='container'>
            <h2 className='text-center'>List of Employees</h2>
            <button type="button" class="btn btn-primary mb-2" onClick={addNewEmployee}>Add Employee</button>
            <table className='table table-stripped table-bordered'>
                <thead>
                    <tr>
                        <th>Employee Id</th>
                        <th>Employee First Name</th>
                        <th>Employee Last Name</th>
                        <th>Employee Email</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        employees.map(data =>
                            <tr key={data.id}>
                                <td>{data.id}</td>
                                <td>{data.firstName}</td>
                                <td>{data.lastName}</td>
                                <td>{data.email}</td>
                                <td>
                                    <button type="button" class="btn btn-info" onClick={() => updateEmployee(data.id)}>Update</button>
                                    <button type="button" class="btn btn-danger m-2" onClick={() => removeEmployee(data.id)}>Delete</button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    )
}

export default ListEmployeeComponent


