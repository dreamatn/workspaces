package com.hisun.DC;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPCSLOSS;
import com.hisun.BP.BPCSOCAC;
import com.hisun.BP.BPRLOSS;
import com.hisun.BP.BPROCAC;
import com.hisun.CI.CICMACR;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class DCZUDDCK {
    boolean pgmRtn = false;
    String CPN_DCZUCINF = "DC-U-CARD-INF";
    String CPN_DCZUCACJ = "DC-U-CARD-AC-JOIN";
    String CPN_CIZMACR = "CI-MAIN-ACR";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String CPN_DCZPCDCK = "DC-P-CARD-TRKDAT-CHK";
    String CPN_CIZMJRL = "CI-MAIN-JRL";
    String K_OUTPUT_FMT = "DC110";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    String WS_ACLNK_VIA_AC = " ";
    int WS_IDX = 0;
    char WS_LOST_STS = ' ';
    int WS_CNT2 = 0;
    int WS_CNT3 = 0;
    String WS_ACLNK_CARD_NO = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DCRACLNK DCRACLNK = new DCRACLNK();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRIAACR DCRIAACR = new DCRIAACR();
    DCCHLOST DCCHLOST = new DCCHLOST();
    DCCHLOST DCCOLOST = new DCCHLOST();
    DCCHLOST DCCNLOST = new DCCHLOST();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCCUCACJ DCCUCACJ = new DCCUCACJ();
    CICMACR CICMACR = new CICMACR();
    CICMJRL CICMJRL = new CICMJRL();
    DCCUCINF DCCUCINF = new DCCUCINF();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICCAGT CICCAGT = new CICCAGT();
    DCRCDPRT DCRCDPRT = new DCRCDPRT();
    DCRCTCDC DCRCTCDC = new DCRCTCDC();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    CICSACR CICSACR = new CICSACR();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    BPRLOSS BPRLOSS = new BPRLOSS();
    BPCSLOSS BPCSLOSS = new BPCSLOSS();
    BPROCAC BPROCAC = new BPROCAC();
    DCCUCDLP DCCUCDLP = new DCCUCDLP();
    EACSBIND EACSBIND = new EACSBIND();
    EACSBINQ EACSBINQ = new EACSBINQ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUDDCK DCCUDDCK;
    public void MP(SCCGWA SCCGWA, DCCUDDCK DCCUDDCK) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUDDCK = DCCUDDCK;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUDDCK return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B110_CHECK_INPUT_RELATE();
        if (pgmRtn) return;
        WS_IDX = 1;
        while ((DCCUDDCK.JOIN_CRD_FLG == 'Y' 
            || WS_IDX <= 1) 
            && ((WS_IDX <= 5 
            && DCCUDDCK.CARD_INFO[WS_IDX-1].CARD_NO.trim().length() != 0) 
            || DCCUDDCK.JOIN_CRD_FLG != 'Y')) {
            B200_GET_CARD_INFO();
            if (pgmRtn) return;
            B300_CHECK_CARD_PSW();
            if (pgmRtn) return;
            if (DCCUDDCK.JOIN_CRD_FLG == 'Y') {
                B400_CHECK_JOINT_CARD();
                if (pgmRtn) return;
            } else {
                B500_CHECK_NON_JOINT_CARD();
                if (pgmRtn) return;
            }
            WS_IDX = WS_IDX + 1;
        }
        B600_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUDDCK.AC_NO);
        CEP.TRC(SCCGWA, DCCUDDCK.JOIN_CRD_FLG);
        if (DCCUDDCK.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_AC_STS_INVALID;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_AC_STS_INVALID, DCCUDDCK.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUDDCK.JOIN_CRD_FLG != 'Y' 
            && DCCUDDCK.JOIN_CRD_FLG != 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_AC_STS_INVALID;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_AC_STS_INVALID, DCCUDDCK.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUDDCK.CARD_INFO[1-1].CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NO_MISSING;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NO_MISSING, DCCUDDCK.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            IBS.init(SCCGWA, DCCUCDLP);
            DCCUCDLP.CARD_NO = DCCUDDCK.CARD_INFO[1-1].CARD_NO;
            DCCUCDLP.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_DCZUCDLP();
            if (pgmRtn) return;
        }
    }
    public void B110_CHECK_INPUT_RELATE() throws IOException,SQLException,Exception {
        if (DCCUDDCK.JOIN_CRD_FLG == 'Y') {
            WS_IDX = 1;
            while (WS_IDX <= 1) {
                if (DCCUDDCK.CARD_INFO[WS_IDX-1].CARD_NO.trim().length() == 0) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MISSING_INPUT_FIELD;
                    IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MISSING_INPUT_FIELD, DCCUDDCK.RC);
                }
                WS_IDX = WS_IDX + 1;
            }
            WS_IDX = 1;
            while ((WS_IDX <= 5 
                && DCCUDDCK.CARD_INFO[WS_IDX-1].CARD_NO.trim().length() != 0)) {
                WS_IDX = WS_IDX + 1;
            }
            WS_IDX = WS_IDX - 1;
            CEP.TRC(SCCGWA, WS_IDX);
            IBS.init(SCCGWA, DCRACLNK);
            WS_ACLNK_VIA_AC = " ";
            DCRACLNK.KEY.CARD_NO = DCCUDDCK.CARD_INFO[1-1].CARD_NO;
            DCRACLNK.CARD_AC_STATUS = '1';
            T000_READ_DCTACLNK_FRIST();
            if (pgmRtn) return;
            WS_ACLNK_VIA_AC = DCRACLNK.VIA_AC;
            IBS.init(SCCGWA, DCRACLNK);
            WS_CNT = 0;
            DCRACLNK.VIA_AC = WS_ACLNK_VIA_AC;
            DCRACLNK.CARD_AC_STATUS = '1';
            T000_STARTBR_DCTACLNK();
            if (pgmRtn) return;
            T000_READNEXT_DCTACLNK();
            if (pgmRtn) return;
            while ((SCCGWA.COMM_AREA.DBIO_FLG == '0')) {
                IBS.init(SCCGWA, DCRCDDAT);
                DCRCDDAT.KEY.CARD_NO = DCRACLNK.KEY.CARD_NO;
                T000_READ_DCTCDDAT_FIRST();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    if (DCRCDDAT.CARD_STS == 'N' 
                        || DCRCDDAT.EXC_CARD_TMS > 1) {
                        WS_CNT = WS_CNT + 1;
                    }
                }
                T000_READNEXT_DCTACLNK();
                if (pgmRtn) return;
            }
            T000_ENDBR_DCTACLNK();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_CNT);
            if (WS_CNT != WS_IDX) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EXCEED_MAX_JOIN_LMT;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_EXCEED_MAX_JOIN_LMT, DCCUDDCK.RC);
            }
        } else {
            WS_IDX = 2;
            while (WS_IDX <= 5) {
                if (DCCUDDCK.CARD_INFO[WS_IDX-1].CARD_NO.trim().length() > 0) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MISSING_INPUT_FIELD;
                    IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MISSING_INPUT_FIELD, DCCUDDCK.RC);
                }
                WS_IDX = WS_IDX + 1;
            }
        }
    }
    public void B200_GET_CARD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DCCUDDCK.CARD_INFO[WS_IDX-1].CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        if (DCCUCINF.CARD_PROD_FLG == 'S') {
            IBS.init(SCCGWA, DCRCTCDC);
            DCRCTCDC.KEY.OLD_CARD_NO = DCCUCINF.CARD_NO;
            T000_READ_DCTCTCDC();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CTCARD_CDC_REC);
            }
        }
        CEP.TRC(SCCGWA, DCCUCINF.CARD_PHY_TYP);
        if (DCCUCINF.CARD_PHY_TYP == 'P') {
            if (DCCUDDCK.JOIN_CRD_FLG != DCCUCINF.JOIN_CUS_FLG) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_JOINT_FLG_NOT_MATCH;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_JOINT_FLG_NOT_MATCH, DCCUDDCK.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCUCINF.CARD_LNK_TYP != '1') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_PRIM_CARD;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_NOT_PRIM_CARD, DCCUDDCK.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
            if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS, DCCUDDCK.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
            if (DCCUCINF.CARD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_PIN_LOCK;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_PIN_LOCK, DCCUDDCK.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
            if (DCCUCINF.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_SWALLOW_STS;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ALR_CARD_SWALLOW_STS, DCCUDDCK.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCUCINF.CARD_STS != 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B300_CHECK_CARD_PSW() throws IOException,SQLException,Exception {
        if (DCCUCINF.PSW_FLG == 'N') {
            if (DCCUDDCK.CARD_INFO[WS_IDX-1].CARD_PSW.trim().length() > 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PSW_NOT_INPUT;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PSW_NOT_INPUT, DCCUDDCK.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (DCCUDDCK.CARD_INFO[WS_IDX-1].CARD_PSW.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PSW_MUST_INPUT;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PSW_MUST_INPUT, DCCUDDCK.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, DCCPCDCK);
        DCCPCDCK.CARD_NO = DCCUDDCK.CARD_INFO[WS_IDX-1].CARD_NO;
        if (DCCUDDCK.CARD_INFO[WS_IDX-1].CARD_PSW.trim().length() == 0) {
            if (DCCUDDCK.CARD_INFO[WS_IDX-1].TRK2_DAT.trim().length() == 0 
                && DCCUDDCK.CARD_INFO[WS_IDX-1].TRK3_DAT.trim().length() == 0) {
                DCCPCDCK.FUNC_CODE = 'N';
                S000_CALL_DCZPCDCK();
                if (pgmRtn) return;
            } else {
                DCCPCDCK.FUNC_CODE = 'T';
                DCCPCDCK.TRK2_DAT = DCCUDDCK.CARD_INFO[WS_IDX-1].TRK2_DAT;
                DCCPCDCK.TRK3_DAT = DCCUDDCK.CARD_INFO[WS_IDX-1].TRK3_DAT;
                S000_CALL_DCZPCDCK();
                if (pgmRtn) return;
            }
        } else {
            if (DCCUDDCK.CARD_INFO[WS_IDX-1].TRK2_DAT.trim().length() == 0 
                && DCCUDDCK.CARD_INFO[WS_IDX-1].TRK3_DAT.trim().length() == 0) {
                DCCPCDCK.FUNC_CODE = 'P';
                DCCPCDCK.CARD_PSW = DCCUDDCK.CARD_INFO[WS_IDX-1].CARD_PSW;
                S000_CALL_DCZPCDCK();
                if (pgmRtn) return;
            } else {
                DCCPCDCK.FUNC_CODE = 'B';
                DCCPCDCK.CARD_PSW = DCCUDDCK.CARD_INFO[WS_IDX-1].CARD_PSW;
                DCCPCDCK.TRK2_DAT = DCCUDDCK.CARD_INFO[WS_IDX-1].TRK2_DAT;
                DCCPCDCK.TRK3_DAT = DCCUDDCK.CARD_INFO[WS_IDX-1].TRK3_DAT;
                S000_CALL_DCZPCDCK();
                if (pgmRtn) return;
            }
        }
    }
    public void B400_CHECK_JOINT_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRACLNK);
        WS_ACLNK_VIA_AC = " ";
        DCRACLNK.KEY.CARD_NO = DCCUDDCK.CARD_INFO[WS_IDX-1].CARD_NO;
        DCRACLNK.CARD_AC_STATUS = '1';
        T000_READ_DCTACLNK_FRIST();
        if (pgmRtn) return;
        WS_ACLNK_VIA_AC = DCRACLNK.VIA_AC;
        IBS.init(SCCGWA, DCRACLNK);
        WS_CNT = 0;
        DCRACLNK.VIA_AC = WS_ACLNK_VIA_AC;
        DCRACLNK.CARD_AC_STATUS = '1';
        T000_STARTBR_DCTACLNK();
        if (pgmRtn) return;
        T000_READNEXT_DCTACLNK();
        if (pgmRtn) return;
        while ((SCCGWA.COMM_AREA.DBIO_FLG == '0')) {
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DCRACLNK.KEY.CARD_NO;
            T000_READ_DCTCDDAT_FIRST();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                if (DCRCDDAT.CARD_STS == 'N' 
                    || DCRCDDAT.EXC_CARD_TMS > 1) {
                    WS_CNT = WS_CNT + 1;
                }
            }
            T000_READNEXT_DCTACLNK();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTACLNK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CNT);
        if (WS_CNT == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_AC_LNK_NOT_FND;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_AC_LNK_NOT_FND, DCCUDDCK.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            T000_GROUP_DCTIAACR();
            if (pgmRtn) return;
            if (WS_CNT3 > 1) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_LINK_OTH_AC;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_LINK_OTH_AC, DCCUDDCK.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            B420_CLOSE_CARD_J();
            if (pgmRtn) return;
        }
    }
    public void B410_CLOSE_CARD() throws IOException,SQLException,Exception {
        B412_UPDATE_CARD_STATUS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRCDDAT.ACNO_TYPE);
        if (DCRCDDAT.ACNO_TYPE == '2' 
            || DCRCDDAT.ACNO_TYPE == '3') {
            B411_II_CARD_CANCEL();
            if (pgmRtn) return;
        }
        B413_CARD_CI_LOG();
        if (pgmRtn) return;
        B414_UPDATE_OCAC_STS();
        if (pgmRtn) return;
        if (DCCUDDCK.JOIN_CRD_FLG == 'Y') {
            B414_CI_NOTE_JOINT_CI();
            if (pgmRtn) return;
        }
    }
    public void B420_CLOSE_CARD_J() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CNT);
        B416_DISJOIN_CARD_VIA();
        if (pgmRtn) return;
        B412_UPDATE_CARD_STATUS();
        if (pgmRtn) return;
        B413_CARD_CI_LOG();
        if (pgmRtn) return;
        if (DCCUDDCK.JOIN_CRD_FLG == 'Y') {
            B414_CI_NOTE_JOINT_CI();
            if (pgmRtn) return;
        }
        B415_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B411_II_CARD_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EACSBINQ);
        EACSBINQ.CARD_NO = DCCUDDCK.CARD_INFO[WS_IDX-1].CARD_NO;
        S000_CALL_EAZSBINQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, EACSBINQ.AC_ARRAY[1-1].CON_AC);
        if (EACSBINQ.AC_ARRAY[1-1].CON_AC.trim().length() > 0) {
            IBS.init(SCCGWA, EACSBIND);
            EACSBIND.FUNC = 'C';
            EACSBIND.CARD_NO = DCCUDDCK.CARD_INFO[WS_IDX-1].CARD_NO;
            EACSBIND.CON_AC = EACSBINQ.AC_ARRAY[1-1].CON_AC;
            EACSBIND.IO_FLG = EACSBINQ.AC_ARRAY[1-1].IO_FLG;
            EACSBIND.CON_BNK = EACSBINQ.AC_ARRAY[1-1].CON_BNK;
            EACSBIND.CON_NME = EACSBINQ.AC_ARRAY[1-1].CON_NME;
            EACSBIND.AC_NM = EACSBINQ.AC_ARRAY[1-1].AC_NM;
            S000_CALL_EAZSBIND();
            if (pgmRtn) return;
        }
    }
    public void B412_UPDATE_CARD_STATUS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCUDDCK.CARD_INFO[WS_IDX-1].CARD_NO;
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_LOST_STS = 'Y';
        }
        DCRCDDAT.CARD_STS = 'C';
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.CLO_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B413_CARD_CI_LOG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'D';
        CICSACR.DATA.AGR_NO = DCCUDDCK.CARD_INFO[WS_IDX-1].CARD_NO;
        CICSACR.DATA.ENTY_TYP = '2';
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
    }
    public void B414_UPDATE_OCAC_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'U';
        BPCSOCAC.AC = DCCUDDCK.CARD_INFO[WS_IDX-1].CARD_NO;
        BPCSOCAC.STS = 'C';
        BPCSOCAC.CLOSE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.CLOSE_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (WS_LOST_STS == 'Y') {
            BPCSOCAC.CLOSE_AC_STS = 'A';
        }
        BPCSOCAC.CLO_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZSOCAC();
        if (pgmRtn) return;
    }
    public void B414_CI_NOTE_JOINT_CI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMJRL);
        CICMJRL.FUNC = 'D';
        CICMJRL.DATA.AC_NO = DCCUDDCK.AC_NO;
        S000_CALL_CIZMJRL();
        if (pgmRtn) return;
    }
    public void B415_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCHLOST);
        IBS.init(SCCGWA, DCCNLOST);
        IBS.init(SCCGWA, DCCOLOST);
        DCCNLOST.CARD_NO = DCCUDDCK.CARD_INFO[WS_IDX-1].CARD_NO;
        DCCNLOST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCCNLOST.LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        DCCNLOST.LAST_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        H000_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B416_DISJOIN_CARD_VIA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRACLNK);
        DCRACLNK.KEY.CARD_NO = DCCUDDCK.CARD_INFO[WS_IDX-1].CARD_NO;
        T000_READUP_DCTACLNK();
        if (pgmRtn) return;
        DCRACLNK.CARD_AC_STATUS = '0';
        DCRACLNK.UPDTBL_DATE = SCCGWA.COMM_AREA.TR_DATE;
        DCRACLNK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTACLNK();
        if (pgmRtn) return;
    }
    public void B500_CHECK_NON_JOINT_CARD() throws IOException,SQLException,Exception {
        if (DCCUCINF.CARD_LNK_TYP == '1') {
            IBS.init(SCCGWA, DCRCDDAT);
            WS_CNT = 0;
            DCRCDDAT.PRIM_CARD_NO = DCCUDDCK.CARD_INFO[WS_IDX-1].CARD_NO;
            T000_STARTBR_DCTCDDAT();
            if (pgmRtn) return;
            T000_READNEXT_DCTCDDAT();
            if (pgmRtn) return;
            while ((SCCGWA.COMM_AREA.DBIO_FLG == '0')) {
                WS_CNT = WS_CNT + 1;
                T000_READNEXT_DCTCDDAT();
                if (pgmRtn) return;
            }
            T000_ENDBR_DCTCDDAT();
            if (pgmRtn) return;
            if (WS_CNT == 0) {
                B410_CLOSE_CARD();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CLOSE_SUP_SUB_CARD;
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CLOSE_SUP_SUB_CARD, DCCUDDCK.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NON_PRIM_CARD_EXIST;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_NON_PRIM_CARD_EXIST, DCCUDDCK.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B600_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        DCCUDDCK.RC.RC_AP = "  ";
        DCCUDDCK.RC.RC_CODE = 0;
    }
    public void T000_READUPD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_UPDATE_DCTCDDAT() throws IOException,SQLException,Exception {
        null.col = "CARD_STS,UPDTBL_DATE,UPDTBL_TLR,CLO_DT";
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.where = "CARD_NO = :DCRCDDAT.KEY.CARD_NO";
        IBS.REWRITE(SCCGWA, DCRCDDAT, this, DCTCDDAT_RD);
    }
    public void T000_READ_DCTACLNK_FRIST() throws IOException,SQLException,Exception {
        DCTACLNK_RD = new DBParm();
        DCTACLNK_RD.TableName = "DCTACLNK";
        DCTACLNK_RD.col = "VIA_AC";
        DCTACLNK_RD.where = "( CARD_NO = :DCRACLNK.KEY.CARD_NO ) "
            + "AND ( CARD_AC_STATUS = :DCRACLNK.CARD_AC_STATUS )";
        DCTACLNK_RD.fst = true;
        IBS.READ(SCCGWA, DCRACLNK, this, DCTACLNK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_AC_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTACLNK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRACLNK.VIA_AC);
        DCTACLNK_BR.rp = new DBParm();
        DCTACLNK_BR.rp.TableName = "DCTACLNK";
        DCTACLNK_BR.rp.where = "VIA_AC = :DCRACLNK.VIA_AC "
            + "AND CARD_AC_STATUS = :DCRACLNK.CARD_AC_STATUS";
        IBS.STARTBR(SCCGWA, DCRACLNK, this, DCTACLNK_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTIAACR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRIAACR.SUB_AC);
        DCTIAACR_BR.rp = new DBParm();
        DCTIAACR_BR.rp.TableName = "DCTIAACR";
        DCTIAACR_BR.rp.where = "SUB_AC = :DCRIAACR.SUB_AC "
            + "AND ACCR_FLG = '1'";
        IBS.STARTBR(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
    }
    public void T000_STARTBR_DCTCDDAT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRCDDAT.PRIM_CARD_NO);
        DCTCDDAT_BR.rp = new DBParm();
        DCTCDDAT_BR.rp.TableName = "DCTCDDAT";
        DCTCDDAT_BR.rp.where = "PRIM_CARD_NO = :DCRCDDAT.PRIM_CARD_NO "
            + "AND CARD_LNK_TYP < > '1' "
            + "AND CARD_STS < > 'C'";
        IBS.STARTBR(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
    }
    public void T000_READNEXT_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
    }
    public void T000_READNEXT_DCTACLNK() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRACLNK, this, DCTACLNK_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRCDDAT, this, DCTCDDAT_BR);
    }
    public void T000_ENDBR_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTIAACR_BR);
    }
    public void T000_ENDBR_DCTCDDAT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTCDDAT_BR);
    }
    public void T000_GROUP_DCTACLNK() throws IOException,SQLException,Exception {
        WS_CNT2 = 0;
        DCTACLNK_RD = new DBParm();
        DCTACLNK_RD.TableName = "DCTACLNK";
        DCTACLNK_RD.col = "VIA_AC";
        DCTACLNK_RD.where = "CARD_NO = :DCRACLNK.KEY.CARD_NO "
            + "AND CARD_AC_STATUS = '1'";
        IBS.READ(SCCGWA, DCRACLNK, this, DCTACLNK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.init(SCCGWA, DCRIAACR);
            DCRIAACR.KEY.VIA_AC = DCRACLNK.VIA_AC;
            DCTIAACR_RD = new DBParm();
            DCTIAACR_RD.TableName = "DCTIAACR";
            DCTIAACR_RD.set = "WS-CNT2=COUNT(*)";
            DCTIAACR_RD.where = "VIA_AC = :DCRIAACR.KEY.VIA_AC "
                + "AND ACCR_FLG = '1'";
            IBS.GROUP(SCCGWA, DCRIAACR, this, DCTIAACR_RD);
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_AC_LNK_NOT_FND;
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_AC_LNK_NOT_FND, DCCUDDCK.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_GROUP_DCTIAACR() throws IOException,SQLException,Exception {
        WS_CNT3 = 0;
        IBS.init(SCCGWA, DCRIAACR);
        DCRIAACR.KEY.VIA_AC = WS_ACLNK_VIA_AC;
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.set = "WS-CNT3=COUNT(*)";
        DCTIAACR_RD.where = "VIA_AC = :DCRIAACR.KEY.VIA_AC "
            + "AND ACCR_FLG = '1'";
        IBS.GROUP(SCCGWA, DCRIAACR, this, DCTIAACR_RD);
    }
    public void T000_READ_DCTCDDAT_FIRST() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.col = "CARD_NO,EXC_CARD_TMS,CARD_STS";
        DCTCDDAT_RD.where = "CARD_NO = :DCRCDDAT.KEY.CARD_NO";
        DCTCDDAT_RD.fst = true;
        DCTCDDAT_RD.order = "EXC_CARD_TMS DESC";
        IBS.READ(SCCGWA, DCRCDDAT, this, DCTCDDAT_RD);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_ENDBR_DCTACLNK() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTACLNK_BR);
    }
    public void T000_READUP_DCTACLNK() throws IOException,SQLException,Exception {
        DCTACLNK_RD = new DBParm();
        DCTACLNK_RD.TableName = "DCTACLNK";
        DCTACLNK_RD.col = "CARD_NO, VIA_AC, CARD_AC_STATUS, CARD_TYPE, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR, TS";
        DCTACLNK_RD.upd = true;
        IBS.READ(SCCGWA, DCRACLNK, DCTACLNK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DCRACLNK.KEY.CARD_NO);
            CEP.TRC(SCCGWA, DCRACLNK.CARD_AC_STATUS);
            if (DCRACLNK.CARD_AC_STATUS == '0') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_AC_LNK_NOT_FND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VIA_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDPRT() throws IOException,SQLException,Exception {
        DCTCDPRT_RD = new DBParm();
        DCTCDPRT_RD.TableName = "DCTCDPRT";
        DCTCDPRT_RD.col = "CARD_NO, PRT_TYP, PRT_NO, PRT_STS, IN_FLG, OUT_FLG, SIGN_DT, CANCEL_DT, RMK, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRCDPRT, DCTCDPRT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDPRT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDPRT_UPD() throws IOException,SQLException,Exception {
        DCTCDPRT_RD = new DBParm();
        DCTCDPRT_RD.TableName = "DCTCDPRT";
        DCTCDPRT_RD.col = "CARD_NO, PRT_TYP, PRT_NO, PRT_STS, IN_FLG, OUT_FLG, SIGN_DT, CANCEL_DT, RMK, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTCDPRT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDPRT, DCTCDPRT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDPRT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTACLNK() throws IOException,SQLException,Exception {
        DCTACLNK_RD = new DBParm();
        DCTACLNK_RD.TableName = "DCTACLNK";
        IBS.REWRITE(SCCGWA, DCRACLNK, DCTACLNK_RD);
    }
    public void T000_READ_DCTCTCDC() throws IOException,SQLException,Exception {
        DCTCTCDC_RD = new DBParm();
        DCTCTCDC_RD.TableName = "DCTCTCDC";
        IBS.READ(SCCGWA, DCRCTCDC, DCTCTCDC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        }
    }
    public void T000_REWRITE_DCTCDPRT() throws IOException,SQLException,Exception {
        DCTCDPRT_RD = new DBParm();
        DCTCDPRT_RD.TableName = "DCTCDPRT";
        IBS.REWRITE(SCCGWA, DCRCDPRT, DCTCDPRT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DUPKEY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDPRT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZPCDCK, DCCPCDCK);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUCINF, DCCUCINF);
        CEP.TRC(SCCGWA, DCCUCINF.RC);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUDDCK.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCACJ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUCACJ, DCCUCACJ);
        if (DCCUCACJ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, DCCUCACJ.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCACJ.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUCACJ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUDDCK.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZMACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CIZMACR, CICMACR);
        if (CICMACR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICMACR.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICMACR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUDDCK.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZMJRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CIZMJRL, CICMJRL);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUDDCK.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHK-AC-AGT", CICCAGT);
