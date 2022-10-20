package com.example.pharmacy.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.pharmacy.enitity.Medicine;
import com.example.pharmacy.enitity.User;
import com.example.pharmacy.exception.NotFoundException;
import com.example.pharmacy.form.MedicineForm;
import com.example.pharmacy.repository.MedicineRepository;
import com.example.pharmacy.service.MedicineService;
import com.example.pharmacy.view.MedicineDetailView;
import com.example.pharmacy.view.MedicineListView;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    // @Autowired
    // private User user;

    @Override
    public Collection<MedicineListView> list() {
        Date date = new Date();
        return medicineRepository.findByStatusAndQuantityGreaterThanAndExpiryDateGreaterThan(Medicine.Status.ACTIVE.value,0,date)
                .stream().map(x -> new MedicineListView(x)).collect(Collectors.toList());

        // return null;
    }
    @Override
    public Collection<MedicineListView> listbyCompanyId(Integer companyId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MedicineDetailView add(MedicineForm form) {
        // System.out.println("jkgsdjgjksdb->"+form.getMedicinename());
        // System.out.println("=====>"+form.getExpiryDate());
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // try {
        //     Date date1 = sdf.parse(form.getExpiryDate().toString());
        // System.out.println("=====>"+date1);

        // } catch (ParseException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        return new MedicineDetailView(medicineRepository.save(new Medicine(form)));
    }

    @Override
    public MedicineDetailView get(Integer medicineId) throws NotFoundException {
        return medicineRepository.findByMedicineIdAndStatus(medicineId, Medicine.Status.ACTIVE.value)
                .map((medicine) -> {
                    return new MedicineDetailView(medicine);
                }).orElseThrow(NotFoundException::new);
    }

    @Override
    public MedicineDetailView update(Integer medicineId, MedicineForm form) throws NotFoundException {
        return medicineRepository.findByMedicineIdAndStatus(medicineId, Medicine.Status.ACTIVE.value)
                .map((company) -> {
                    return new MedicineDetailView(medicineRepository.save(company.update(form)));
                }).orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(Integer medicineId) throws NotFoundException {
        medicineRepository.deleteMedicine(medicineId);

    }
    @Override
    public Collection<MedicineListView> listExpired() {
        Date date = new Date();
        return medicineRepository.findByStatusAndExpiryDateLessThan(Medicine.Status.ACTIVE.value,date)
                .stream().map(x -> new MedicineListView(x)).collect(Collectors.toList());

    }

    @Scheduled(cron = "* * * * * *")
    public void medicienalert()
    {
        
    try {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("devndn8900@gmail.com");
        // helper.setTo(user.getEmail());
        helper.setTo("devndn8900@gmail.com");
        helper.setSubject("<><><><><><><><><><><><><><><><><><><><>");
        String content = "<h3>"
                // + user.getEmail() + "</h3><br>"
                + "Thank you,<br>";
        helper.setText(content, true);
        System.out.println(content);
        System.out.println(mimeMessage);
        javaMailSender.send(mimeMessage);

    } catch (MessagingException e) {
        e.printStackTrace();
    }
    }
    

}