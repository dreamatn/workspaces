package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTMOVB {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTCMOV_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTMOVB";
    String K_TBL_CMOV = "BPTCMOV ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRCMOV BPRCMOV = new BPRCMOV();
    int WS_TMOVB_BR = 0;
    String WS_TMOVB_TLR = " ";
    SCCGWA SCCGWA;
    BPCTMOVB BPCTMOVB;
    BPRCMOV BPRCMOV1;
    public void MP(SCCGWA SCCGWA, BPCTMOVB BPCTMOVB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTMOVB = BPCTMOVB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTMOVB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRCMOV1 = (BPRCMOV) BPCTMOVB.POINTER;
        CEP.TRC(SCCGWA, BPCTMOVB.DATA_LEN);
        IBS.init(SCCGWA, BPRCMOV);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCMOV1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRCMOV1, BPRCMOV);
        WS_TMOVB_BR = BPCTMOVB.BR;
        WS_TMOVB_TLR = BPCTMOVB.TLR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCTMOVB.FUNC == 'S') {
            B010_STARTBR_PROC();
            if (pgmRtn) return;
        } else if (BPCTMOVB.FUNC == 'I') {
            B040_STARTBR_ATM_IN();
            if (pgmRtn) return;
        } else if (BPCTMOVB.FUNC == 'O') {
            B050_STARTBR_ATM_OUT();
            if (pgmRtn) return;
        } else if (BPCTMOVB.FUNC == 'C') {
            B060_STARTBR_ATM_CHKMOV();
            if (pgmRtn) return;
        } else if (BPCTMOVB.FUNC == 'M') {
            B050_STARTBR_ATM_OUTMOV();
            if (pgmRtn) return;
        } else if (BPCTMOVB.FUNC == 'R') {
            B020_READNEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCTMOVB.FUNC == 'E') {
            B030_ENDBR_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCTMOVB.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCMOV);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRCMOV, BPRCMOV1);
    }
    public void B010_STARTBR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCTMOVB.TLR);
        CEP.TRC(SCCGWA, BPRCMOV.MOV_TYP);
        CEP.TRC(SCCGWA, BPRCMOV.KEY.CASH_TYP);
        CEP.TRC(SCCGWA, BPRCMOV.KEY.CCY);
        CEP.TRC(SCCGWA, BPRCMOV.MOV_STS);
        CEP.TRC(SCCGWA, BPRCMOV.KEY.MOV_DT);
        if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV01");
            T000_STARTBR_BR();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV02");
            T000_STARTBR1_BR();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV03");
            T000_STARTBR_BR_STS();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV04");
            T000_STARTBR1_BR_STS();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV05");
            T000_STARTBR_BR_TLR();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV06");
            T000_STARTBR1_BR_TLR();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV07");
            T000_STARTBR_BR_STS_TLR();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV08");
            T000_STARTBR1_BR_STS_TLR();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV09");
            T000_STARTBR_BR_TYP();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV10");
            T000_STARTBR1_BR_TYP();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV11");
            T000_STARTBR_BR_STS_TYP();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV12");
            T000_STARTBR1_BR_STS_TYP();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV13");
            T000_STARTBR_BR_CCY();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV14");
            T000_STARTBR1_BR_CCY();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV15");
            T000_STARTBR_BR_STS_CCY();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV16");
            T000_STARTBR1_BR_STS_CCY();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV17");
            T000_STARTBR_BR_TLR_TYP();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV18");
            T000_STARTBR1_BR_TLR_TYP();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV19");
            T000_STARTBR_BR_STS_TLR_TYP();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV20");
            T000_STARTBR1_BR_STS_TLR_TYP();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV21");
            T000_STARTBR_BR_TLR_CCY();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV22");
            T000_STARTBR1_BR_TLR_CCY();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV23");
            T000_STARTBR_BR_STS_TLR_CCY();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV24");
            T000_STARTBR1_BR_STS_TLR_CCY();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV25");
            T000_STARTBR_BR_TYP_CCY();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV26");
            T000_STARTBR1_BR_TYP_CCY();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV27");
            T000_STARTBR_BR_STS_TYP_CCY();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV28");
            T000_STARTBR1_BR_STS_TYP_CCY();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV29");
            T000_STARTBR_BY_ALL();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV30");
            T000_STARTBR1_BY_ALL();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV31");
            T000_STARTBR_BY_STS_ALL();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() == 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV32");
            T000_STARTBR1_BY_STS_ALL();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV33");
            T000_STARTBR_BR1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV34");
            T000_STARTBR1_BR1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV35");
            T000_STARTBR_BR_STS1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV36");
            T000_STARTBR1_BR_STS1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV37");
            T000_STARTBR_BR_TLR1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV38");
            T000_STARTBR1_BR_TLR1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV39");
            T000_STARTBR_BR_STS_TLR1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV40");
            T000_STARTBR1_BR_STS_TLR1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV41");
            T000_STARTBR_BR_TYP1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV42");
            T000_STARTBR1_BR_TYP1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV43");
            T000_STARTBR_BR_STS_TYP1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV44");
            T000_STARTBR1_BR_STS_TYP1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV45");
            T000_STARTBR_BR_CCY1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV46");
            T000_STARTBR1_BR_CCY1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV47");
            T000_STARTBR_BR_STS_CCY1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV48");
            T000_STARTBR1_BR_STS_CCY1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV49");
            T000_STARTBR_BR_TLR_TYP1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV50");
            T000_STARTBR1_BR_TLR_TYP1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV51");
            T000_STARTBR_BR_STS_TLR_TYP1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() == 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV52");
            T000_STARTBR1_BR_STS_TLR_TYP1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV53");
            T000_STARTBR_BR_TLR_CCY1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV54");
            T000_STARTBR1_BR_TLR_CCY1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV55");
            T000_STARTBR_BR_STS_TLR_CCY1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP == ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV56");
            T000_STARTBR1_BR_STS_TLR_CCY1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV57");
            T000_STARTBR_BR_TYP_CCY1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV58");
            T000_STARTBR1_BR_TYP_CCY1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV59");
            T000_STARTBR_BR_STS_TYP_CCY1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() == 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV60");
            T000_STARTBR1_BR_STS_TYP_CCY1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV61");
            T000_STARTBR_BY_ALL1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS == ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV62");
            T000_STARTBR1_BY_ALL1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT == 0) {
            CEP.TRC(SCCGWA, "DEV63");
            T000_STARTBR_BY_STS_ALL1();
            if (pgmRtn) return;
        } else if (BPCTMOVB.TLR.trim().length() > 0 
                && BPRCMOV.MOV_TYP != ' ' 
                && BPRCMOV.KEY.CASH_TYP.trim().length() > 0 
                && BPRCMOV.KEY.CCY.trim().length() > 0 
                && BPRCMOV.MOV_STS != ' ' 
                && BPRCMOV.KEY.MOV_DT != 0) {
            CEP.TRC(SCCGWA, "DEV64");
            T000_STARTBR1_BY_STS_ALL1();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "DEV65");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTMOVB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTCMOV();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTCMOV();
        if (pgmRtn) return;
    }
    public void B040_STARTBR_ATM_IN() throws IOException,SQLException,Exception {
        T000_STARTBR_ATM_IN();
        if (pgmRtn) return;
    }
    public void B050_STARTBR_ATM_OUT() throws IOException,SQLException,Exception {
        T000_STARTBR_ATM_OUT();
        if (pgmRtn) return;
    }
    public void B060_STARTBR_ATM_CHKMOV() throws IOException,SQLException,Exception {
        T000_STARTBR_ATM_CHKMOV();
        if (pgmRtn) return;
    }
    public void B050_STARTBR_ATM_OUTMOV() throws IOException,SQLException,Exception {
        T000_STARTBR_ATM_OUTMOV();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_ATM_IN() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "CCY = :BPRCMOV.KEY.CCY "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND IN_BR = :BPRCMOV.IN_BR "
            + "AND IN_TLR = :BPRCMOV.IN_TLR";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_ATM_OUT() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "MOV_DT = :BPRCMOV.KEY.MOV_DT "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND OUT_BR = :BPRCMOV.OUT_BR "
            + "AND OUT_TLR = :BPRCMOV.OUT_TLR";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_ATM_CHKMOV() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "CCY = :BPRCMOV.KEY.CCY "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND IN_BR = :BPRCMOV.IN_BR "
            + "AND IN_TLR = :BPRCMOV.IN_TLR";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_ATM_OUTMOV() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "CCY = :BPRCMOV.KEY.CCY "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND OUT_BR = :BPRCMOV.OUT_BR "
            + "AND OUT_TLR = :BPRCMOV.OUT_TLR";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "OR IN_BR = :WS_TMOVB_BR )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_STS() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_STS = :BPRCMOV.MOV_STS ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND MOV_STS = :BPRCMOV.MOV_STS )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_STS() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_TLR() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_TLR() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_STS_TLR() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_STS = :BPRCMOV.MOV_STS ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND MOV_STS = :BPRCMOV.MOV_STS )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_STS_TLR() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_TYP() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_TYP() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_STS_TYP() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_STS_TYP() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_CCY() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND CCY = :BPRCMOV.KEY.CCY ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND CCY = :BPRCMOV.KEY.CCY )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_CCY() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_STS_CCY() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_STS_CCY() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_TLR_TYP() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_TLR_TYP() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_STS_TLR_TYP() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_STS_TLR_TYP() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_TLR_CCY() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND CCY = :BPRCMOV.KEY.CCY ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND CCY = :BPRCMOV.KEY.CCY )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_TLR_CCY() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_STS_TLR_CCY() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_STS_TLR_CCY() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_TYP_CCY() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_TYP_CCY() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_STS_TYP_CCY() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_STS_TYP_CCY() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BY_STS_ALL() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_STS "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BY_STS_ALL() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_STS "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BY_ALL() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BY_ALL() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_STS1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_STS1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_TLR1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_TLR1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_STS_TLR1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_STS_TLR1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_TYP1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_TYP1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_STS_TYP1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_STS_TYP1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_CCY1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_CCY1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_STS_CCY1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_STS_CCY1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_TLR_TYP1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_TLR_TYP1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_STS_TLR_TYP1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_STS_TLR_TYP1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_TLR_CCY1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_TLR_CCY1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_STS_TLR_CCY1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_STS_TLR_CCY1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_TYP_CCY1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_TYP_CCY1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BR_STS_TYP_CCY1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BR_STS_TYP_CCY1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BY_STS_ALL1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BY_STS_ALL1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_STS = :BPRCMOV.MOV_STS "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR_BY_ALL1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_STARTBR1_BY_ALL1() throws IOException,SQLException,Exception {
        BPTCMOV_BR.rp = new DBParm();
        BPTCMOV_BR.rp.TableName = "BPTCMOV";
        BPTCMOV_BR.rp.where = "( OUT_TLR = :WS_TMOVB_TLR "
            + "AND OUT_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT ) "
            + "OR ( IN_TLR = :WS_TMOVB_TLR "
            + "AND IN_BR = :WS_TMOVB_BR "
            + "AND MOV_TYP = :BPRCMOV.MOV_TYP "
            + "AND CASH_TYP = :BPRCMOV.KEY.CASH_TYP "
            + "AND CCY = :BPRCMOV.KEY.CCY "
            + "AND MOV_DT = :BPRCMOV.KEY.MOV_DT )";
        BPTCMOV_BR.rp.order = "MOV_DT DESC";
        IBS.STARTBR(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
    }
    public void T000_READNEXT_BPTCMOV() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRCMOV, this, BPTCMOV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTMOVB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTMOVB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTCMOV() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCMOV_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTMOVB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTMOVB = ");
            CEP.TRC(SCCGWA, BPCTMOVB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
