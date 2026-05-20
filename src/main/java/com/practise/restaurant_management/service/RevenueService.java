package com.practise.restaurant_management.service;

import com.practise.restaurant_management.dto.request.ViewReportRequestDto;
import com.practise.restaurant_management.dto.response.ViewReportResponseDto;
import com.practise.restaurant_management.entity.Branches;
import com.practise.restaurant_management.entity.Order;
import com.practise.restaurant_management.entity.Revenue;
import com.practise.restaurant_management.entity.User;
import com.practise.restaurant_management.repo.RevenueRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RevenueService {


    private final OrderService orderService;
    private final ExpenditureService expenditureService;
    private final BranchService branchService;
    private final RevenueRepo revenueRepo;
    private final JavaMailSender mailSender;

    @Scheduled(cron = "0 0 5 * * *")
    public void generateDailyReport() {
        List<Branches> branchesList = branchService.getAll();
        for(Branches branch : branchesList) {
            Double totalIncome = branch.getStaffList().stream().map(s -> s.getOrder()).flatMap(o->o.stream()).filter(o->o.getOrderedAt().toString().contains(LocalDate.now().toString())).map(o->o.getFinalPrice()).reduce(0D,(a, b)->a+b);
            Double totalExpenditure = branch.getExpenditureList().stream().filter(e->e.getDate().toString().contains(LocalDate.now().toString())).map(a->a.getAmount()).reduce(0D, (a,b)->a+b);
            Long totalOrders = branch.getStaffList().stream().map(s->s.getOrder()).flatMap(o->o.stream()).filter(o->o.getOrderedAt().toString().contains(LocalDate.now().toString())).count();
            Double totalProfit = totalIncome - totalExpenditure;
            Double profitPercentage = 0.0;
            if(totalIncome > 0) {
                profitPercentage = (totalProfit / totalIncome) * 100;
            }

            Revenue revenue = Revenue.builder()
                    .branch(branchService.getById(branch.getId()))
                    .income(totalIncome)
                    .expenditureAmount(totalExpenditure)
                    .totalOrders(totalOrders)
                    .profit(totalProfit)
                    .date(LocalDate.now())
                    .build();

            revenueRepo.save(revenue);
            log.info("Saved the daily report for the date: {}", revenue.getDate());

            User branchManager = branch.getManager();
            sendMail(revenue, branchManager.getEmail());
            log.info("Sent mail to the manager: {}", branchManager.getFirstName());
            User restaurantOwner = branch.getRestaurant().getOwner();
            sendMail(revenue, restaurantOwner.getEmail());
            log.info("Sent mail to the restaurant owner: {}", restaurantOwner.getFirstName());
        }
    }




    public void sendMail(Revenue request, String receiverMail) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("mikhel.pottella@coditas.com");
        simpleMailMessage.setTo(receiverMail);
        simpleMailMessage.setSubject("Daily Report of Branch");
        simpleMailMessage.setText("BranchId: " + request.getBranch().getId() + "\nTotalIncome: " + request.getIncome()
                + "\nTotalExpenditure: " + request.getExpenditureAmount() + "\nTotalOrders: " + request.getTotalOrders() + "\nTotalProfit: " + request.getProfit()
                 + "\nReportDate: " + request.getDate()
                + "\n\nThis is the daily report of branch: " + request.getBranch().getId());
        mailSender.send(simpleMailMessage);
    }

    public List<ViewReportResponseDto> viewReports(ViewReportRequestDto request) {
        List<Revenue> response = revenueRepo.extractDetailsBtw(request.branchId(),request.from(),request.to());

        List<ViewReportResponseDto> viewReportResponseDtos = response.stream().map(r-> new ViewReportResponseDto(r.getBranch().getId(), r.getIncome(), r.getExpenditureAmount(), r.getTotalOrders(), r.getProfit(), r.getProfit())).toList();

        return viewReportResponseDtos;
    }
}