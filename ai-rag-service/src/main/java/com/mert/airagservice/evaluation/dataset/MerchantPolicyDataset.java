package com.mert.airagservice.evaluation.dataset;

import com.mert.airagservice.evaluation.dto.RagTestCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MerchantPolicyDataset {
    public List<RagTestCase> getTestCases() {
        return List.of(

                new RagTestCase(
                        "Who is legally responsible for refunds on the Mert marketplace?",
                        "The merchant is ultimately responsible for fulfilling statutory warranties and refunds."
                ),

                new RagTestCase(
                        "Can merchants refuse returns if product is defective?",
                        "No, defective products must be accepted for return or replacement as per policy."
                ),

                new RagTestCase(
                        "What happens if a customer returns an empty box claiming a smartphone return?",
                        "Merchant must file fraud claim with unboxing video and can get reimbursed via MPF if verified."
                ),

                new RagTestCase(
                        "What is the return window for consumer electronics?",
                        "7 days replacement only for dead on arrival or damaged items."
                ),

                new RagTestCase(
                        "Are refunds allowed for buyer's remorse in electronics?",
                        "No refunds are allowed, only replacement in valid defect cases."
                ),

                new RagTestCase(
                        "What is required during reverse pickup QC?",
                        "Verification of SKU, serial numbers, tags, packaging, and absence of damage."
                ),

                new RagTestCase(
                        "What happens if return inwarding is not completed within 48 hours?",
                        "Return is auto approved and refund is issued to customer."
                ),

                new RagTestCase(
                        "Who pays return shipping when the return is merchant attributable?",
                        "The merchant pays reverse shipping fees."
                ),
//
//                new RagTestCase(
//                        "What is an RTO order?",
//                        "Order not delivered due to wrong address, rejection, or logistics failure before delivery."
//                ),
//
//                new RagTestCase(
//                        "Who bears cost for RTO caused by logistics failure?",
//                        "Merchant is not charged unless SLA breach caused the issue."
//                ),
//
//                new RagTestCase(
//                        "What is the return window for apparel?",
//                        "14 days return, refund, or exchange if unworn and with tags."
//                ),
//
//                new RagTestCase(
//                        "What makes apparel ineligible for return?",
//                        "If worn, washed, or missing tags and packaging."
//                ),
//
//                new RagTestCase(
//                        "What is policy for innerwear returns?",
//                        "Strictly non-returnable due to hygiene regulations."
//                ),
//
//                new RagTestCase(
//                        "What happens if cosmetics seal is broken?",
//                        "Item becomes non-returnable unless delivered damaged or expired."
//                ),
//
//                new RagTestCase(
//                        "What is return rule for groceries?",
//                        "Non-returnable, immediate refund only if spoiled or expired at delivery."
//                ),
//
//                new RagTestCase(
//                        "What is return window for large appliances?",
//                        "10 days with technician verification required."
//                ),
//
//                new RagTestCase(
//                        "What happens if appliance installed by uncertified technician?",
//                        "Return policy becomes void."
//                ),
//
                new RagTestCase(
                        "Customer returned stating Item Not As Described but listing is perfectly accurate",
                        "No, only if confirmed incorrect images or wrong product variation was shipped."
                ),
//
//                new RagTestCase(
//                        "What is required for jewelry returns?",
//                        "Exact weight match within 0.01g and certificate verification."
//                ),
//
//                new RagTestCase(
//                        "What is return policy for books?",
//                        "3 days replacement only for missing or misprinted pages."
//                ),
//
//                new RagTestCase(
//                        "Are digital keys refundable?",
//                        "No, software keys are non-returnable and non-refundable."
//                ),

                new RagTestCase(
                        "What happens if courier loses a reverse shipment?",
                        "Merchant is reimbursed through MPF after 21 days loss confirmation."
                ),
//
//                new RagTestCase(
//                        "What is MPF?",
//                        "Merchant Protection Fund compensating verified merchant losses."
//                ),
//
//                new RagTestCase(
//                        "What is required for MPF claim?",
//                        "Unboxing video, photos, and submission within 48 hours."
//                ),
//
//                new RagTestCase(
//                        "What happens if merchant misses MPF claim deadline?",
//                        "Claim is rejected automatically."
//                ),
//
//                new RagTestCase(
//                        "What is SLA for customer message response?",
//                        "12 hours, otherwise merchant ranking drops."
//                ),
//
//                new RagTestCase(
//                        "What happens if replacement is not shipped within 48 hours?",
//                        "System issues refund to customer and charges merchant."
//                ),
//
//                new RagTestCase(
//                        "What is return window for baby care products?",
//                        "5 days replacement only if sealed."
//                ),
//
//                new RagTestCase(
//                        "What happens if returned footwear box is damaged?",
//                        "Merchant can claim up to 25 percent value loss deduction."
//                ),
//
//                new RagTestCase(
//                        "What is required for electronics return acceptance?",
//                        "IMEI match, factory reset, and no cloud lock."
//                ),
//
//                new RagTestCase(
//                        "What happens if device is cloud locked?",
//                        "Return is rejected and sent back to customer."
//                ),
//
//                new RagTestCase(
//                        "What is B2B return limit?",
//                        "Maximum 5 percent of order volume with defects only."
//                ),
//
//                new RagTestCase(
//                        "Are B2B orders eligible for change of mind returns?",
//                        "No, only defects or wrong items are allowed."
//                ),
//
//                new RagTestCase(
//                        "What is restocking fee for B2B returns?",
//                        "15 percent of invoice value."
//                ),
//
//                new RagTestCase(
//                        "What is fraud penalty for counterfeit goods?",
//                        "₹100,000 fine and possible permanent suspension."
//                ),
//
//                new RagTestCase(
//                        "What happens if merchant harasses customer?",
//                        "₹50,000 fine and termination of account."
//                ),
//
//                new RagTestCase(
//                        "What happens if merchant bypasses platform refunds?",
//                        "₹10,000 penalty and visibility reduction."
//                ),
//
//                new RagTestCase(
//                        "What is allowed communication channel for disputes?",
//                        "Only platform messaging, no direct contact allowed."
//                ),
//
//                new RagTestCase(
//                        "What is payment refund time for UPI?",
//                        "24 to 48 hours."
//                ),
//
//                new RagTestCase(
//                        "What is refund time for credit cards?",
//                        "5 to 7 working days."
//                ),
//
//                new RagTestCase(
//                        "What happens in COD refund?",
//                        "Refund goes to wallet or bank account provided by customer."
//                ),
//
//                new RagTestCase(
//                        "What is rule for expired cosmetics dispute?",
//                        "Refund depends on batch verification and logged expiry data."
//                ),
//
//                new RagTestCase(
//                        "What is requirement for warehouse record retention?",
//                        "8 years mandatory storage of invoices, videos, and records."
//                ),
//
//                new RagTestCase(
//                        "What happens if merchant fails to log batch number?",
//                        "System favors consumer evidence and merchant is liable."
//                ),
//
//                new RagTestCase(
//                        "What is return window for automotive parts?",
//                        "7 days, must be unused and sealed."
//                ),
//
//                new RagTestCase(
//                        "What is policy for food returns?",
//                        "Non-returnable, immediate refund only if damaged or expired."
//                ),
//
//                new RagTestCase(
//                        "What is return rule for home textiles?",
//                        "Must be clean, folded, and odor free or return is rejected."
//                ),

                new RagTestCase(
                        "What happens if merchant misses dispute evidence deadline?",
                        "Case is closed in favor of consumer."
                )
//
//                new RagTestCase(
//                        "What is escalation path for disputes?",
//                        "Tier 1 support, Tier 2 arbitration panel, then legal jurisdiction in New Delhi."
//                ),
//
//                new RagTestCase(
//                        "What is policy on unboxing video requirement?",
//                        "Must be continuous, unedited video showing full package opening and inspection."
//                ),

                // =========================
                // CORE LIABILITY & LEGALITY
                // =========================

//                new RagTestCase(
//                        "Who is ultimately liable for refunds on the platform?",
//                        "Merchant is ultimately liable for statutory refunds and warranties."
//                ),
//
//                new RagTestCase(
//                        "If product is correctly described but customer still returns it, who bears liability?",
//                        "Merchant still bears liability for return costs if return is approved under policy conditions."
//                ),
//
//                new RagTestCase(
//                        "Can merchant refuse refund if listing was accurate?",
//                        "No, refusal is not allowed if return qualifies under platform rules."
//                ),
//
//                new RagTestCase(
//                        "When does merchant become NOT liable for return?",
//                        "Only when fraud is proven or return violates category restrictions or policy exceptions."
//                ),
//
//                new RagTestCase(
//                        "If customer changes mind but product is fine, is merchant liable?",
//                        "Yes, for eligible categories merchant is still liable under return window rules."
//                ),
//
//                new RagTestCase(
//                        "Can merchant deny return for valid 'item not as described' claim?",
//                        "No, such claims are auto-approved initially and must be processed."
//                ),

                // =========================
                // SLA FAILURE EDGE CASES
                // =========================

//                new RagTestCase(
//                        "What happens if merchant misses 48-hour inwarding deadline?",
//                        "Return is auto-approved and refund is processed to customer."
//                ),
//
//                new RagTestCase(
//                        "What happens if merchant misses dispute submission deadline?",
//                        "Dispute is closed in favor of consumer."
//                ),
//
//                new RagTestCase(
//                        "What happens if merchant does not ship replacement in 48 hours?",
//                        "System cancels replacement and issues refund charged to merchant."
//                ),
//
//                new RagTestCase(
//                        "What is consequence of delayed customer message response?",
//                        "Merchant ranking and visibility are reduced."
//                ),

                // =========================
                // FRAUD & EMPTY BOX EDGE CASES
                // =========================

//                new RagTestCase(
//                        "Customer returns empty box for high-value item, what should merchant do?",
//                        "Merchant must file fraud claim with unboxing video for MPF reimbursement."
//                ),
//
//                new RagTestCase(
//                        "What evidence is required for fraud claim?",
//                        "Continuous unboxing video, tracking label visibility, and packaging inspection."
//                ),
//
//                new RagTestCase(
//                        "What happens if merchant fails to submit unboxing video?",
//                        "Fraud claim is rejected."
//                ),
//
//                new RagTestCase(
//                        "How is empty box fraud verified?",
//                        "Weight comparison and logistics hub scan analysis."
//                ),
//
//                new RagTestCase(
//                        "What is maximum reimbursement for fraud cases?",
//                        "Up to 100 percent product value capped at ₹25,000."
//                ),

                // =========================
                // RTO EDGE CASES
                // =========================

//                new RagTestCase(
//                        "What is RTO shipment?",
//                        "Shipment returned before delivery due to rejection or logistics failure."
//                ),
//
//                new RagTestCase(
//                        "Who pays for RTO due to wrong address?",
//                        "Merchant is generally not charged unless SLA breach occurred."
//                ),
//
//                new RagTestCase(
//                        "What if RTO happens due to late dispatch?",
//                        "Merchant bears logistics cost."
//                ),

                // =========================
                // CATEGORY EXCEPTIONS (VERY IMPORTANT)
                // =========================

//                new RagTestCase(
//                        "Are cosmetics returnable after seal is broken?",
//                        "No, they become non-returnable unless delivered damaged or expired."
//                ),
//
//                new RagTestCase(
//                        "Are groceries returnable?",
//                        "No, only immediate refund if spoiled or expired at delivery."
//                ),
//
//                new RagTestCase(
//                        "Are innerwear items returnable?",
//                        "No, they are strictly non-returnable."
//                ),
//
//                new RagTestCase(
//                        "What is return policy for jewelry?",
//                        "Strict verification with weight and certificate matching required."
//                ),
//
//                new RagTestCase(
//                        "What if jewelry weight mismatch exceeds 0.02g?",
//                        "It triggers fraud investigation."
//                ),
//
//                new RagTestCase(
//                        "Can books be refunded for change of mind?",
//                        "No, only replacement for defects like missing pages."
//                ),

                // =========================
                // ELECTRONICS EDGE CASES
                // =========================

//                new RagTestCase(
//                        "What conditions must electronics meet for return acceptance?",
//                        "Matching IMEI, factory reset, and no cloud lock."
//                ),
//
//                new RagTestCase(
//                        "What happens if device is iCloud or Google locked?",
//                        "Return is rejected."
//                ),
//
//                new RagTestCase(
//                        "What if IMEI mismatch is found?",
//                        "Return is rejected and fraud flagged."
//                ),
//
//                new RagTestCase(
//                        "Are opened laptops eligible for return?",
//                        "Only if defect or incorrect item is verified."
//                ),

                // =========================
                // APPAREL EDGE CASES
                // =========================

//                new RagTestCase(
//                        "What invalidates apparel return?",
//                        "Worn, washed, or missing tags."
//                ),
//
//                new RagTestCase(
//                        "What is wardrobing fraud?",
//                        "Using apparel once and returning it in damaged condition."
//                ),
//
//                new RagTestCase(
//                        "How is worn footwear detected?",
//                        "Outsole dirt, creasing, or wear marks."
//                ),
//
//                new RagTestCase(
//                        "What happens if security tag is removed?",
//                        "Return is rejected."
//                ),

                // =========================
                // LOGISTICS DAMAGE EDGE CASES
                // =========================

//                new RagTestCase(
//                        "What should merchant do if return package is damaged?",
//                        "Accept delivery and file claim with unboxing video."
//                ),
//
//                new RagTestCase(
//                        "Can merchant reject damaged courier delivery?",
//                        "No, rejection is prohibited."
//                ),
//
//                new RagTestCase(
//                        "What if merchant rejects damaged return package?",
//                        "They lose right to claim reimbursement."
//                ),

                // =========================
                // PAYMENT & REFUND ROUTING
                // =========================

//                new RagTestCase(
//                        "How fast are UPI refunds processed?",
//                        "24 to 48 hours."
//                ),
//
//                new RagTestCase(
//                        "How long do card refunds take?",
//                        "5 to 7 working days."
//                ),
//
//                new RagTestCase(
//                        "Where does COD refund go?",
//                        "Mert wallet or verified bank account."
//                ),
//
//                new RagTestCase(
//                        "Can refund be issued to third party account?",
//                        "No, only verified customer account allowed."
//                ),

                // =========================
                // B2B EDGE CASES
                // =========================

//                new RagTestCase(
//                        "What is return policy for B2B orders?",
//                        "No change-of-mind returns, only defects allowed."
//                ),
//
//                new RagTestCase(
//                        "What is maximum B2B defect allowance?",
//                        "Up to 5 percent of order volume."
//                ),
//
//                new RagTestCase(
//                        "Are B2B refunds allowed for all items?",
//                        "No, industrial machinery has zero return policy."
//                ),
//
//                new RagTestCase(
//                        "What is B2B restocking fee?",
//                        "15 percent of invoice value."
//                ),

                // =========================
                // MPF & PROTECTION FUND EDGE CASES
                // =========================

//                new RagTestCase(
//                        "What is Merchant Protection Fund?",
//                        "A fund that reimburses verified merchant losses."
//                ),
//
//                new RagTestCase(
//                        "When is MPF claim rejected?",
//                        "If evidence is missing or deadline is missed."
//                ),
//
//                new RagTestCase(
//                        "What happens if merchant submits fake evidence?",
//                        "Claim is rejected and penalty applied."
//                ),

                // =========================
                // SLAs + SYSTEM AUTOMATION EDGE
                // =========================

//                new RagTestCase(
//                        "What triggers automated refund approval?",
//                        "Missing inwarding or SLA expiration."
//                ),
//
//                new RagTestCase(
//                        "Is manual override allowed for SLA system?",
//                        "Only through dispute escalation channels."
//                ),

                // =========================
                // LEGAL + COMPLIANCE
                // =========================

//                new RagTestCase(
//                        "What law governs this policy?",
//                        "Consumer Protection (E-Commerce) Rules 2020 and IT Act 2000."
//                ),
//
//                new RagTestCase(
//                        "Can merchants bypass platform refunds privately?",
//                        "No, it is penalized."
//                ),
//
//                new RagTestCase(
//                        "What is penalty for counterfeit goods?",
//                        "₹100,000 fine and possible permanent suspension."
//                ),
//
//                new RagTestCase(
//                        "What is data retention requirement?",
//                        "8 years mandatory retention of records."
//                ),
//
//                new RagTestCase(
//                        "What happens if merchant refuses audit?",
//                        "Account may be suspended."
//
//                )
        );
    }
}
