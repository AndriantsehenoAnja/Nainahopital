<?php

namespace App\Controllers;


class PatientController extends BaseController
{
    public function findPatientApi($id)
    {
        $client = \Config\Services::curlrequest();

        try {
            $response = $client->get(
                'http://localhost:8080/NainaExtrat/patient?id='.$id,
                [
                    'http_errors' => false
                ]
            );

            if ($response->getStatusCode() !== 200) {
                return view('patient/fiche', [
                    'patient' => null
                ]);
            }

            $data = json_decode($response->getBody(), true);

            return view('patient/fiche', [
                'patient' => $data
            ]);

        } catch (\Throwable $e) {
            return view('patient/fiche', [
                'patient' => null
            ]);
        }
    }
}
