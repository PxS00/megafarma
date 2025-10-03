package br.com.fiap.dao;

import java.time.LocalDate;
import java.util.ArrayList;

public class RemedioDAO {
    public ArrayList<br.com.fiap.to.RemedioTO> findAll(){
        ArrayList<br.com.fiap.to.RemedioTO>remedios = new ArrayList<>();
        br.com.fiap.to.RemedioTO remedio = new br.com.fiap.to.RemedioTO(1L, "Loratadina", 7.93, LocalDate.parse("2023-10-10"), LocalDate.parse("2025-10-10"));

        remedios.add(remedio);

        remedio = new br.com.fiap.to.RemedioTO(2L, "Amoxicilina", 26.50, LocalDate.now(), LocalDate.now().plusYears(2));
        remedios.add(remedio);
        return remedios;

    };
}
