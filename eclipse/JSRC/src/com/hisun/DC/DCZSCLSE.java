package com.hisun.DC;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPFPDT;
import com.hisun.BP.BPCPLOSS;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPORUP_DATA_INFO;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPCSLOSS;
import com.hisun.BP.BPCSOCAC;
import com.hisun.BP.BPRLOSS;
import com.hisun.BP.BPROCAC;
import com.hisun.CI.CICMACR;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class DCZSCLSE {
    boolean pgmRtn = false;
    String CPN_DCZUPSWC = "DC-U-PSW-CHK";
    String CPN_CIZMACR = "CI-MAIN-ACR";
    String CPN_DCZDQPRD = "DC-P-QUERY-PROD";
    String CPN_DCZUCACJ = "DC-U-CARD-AC-JOIN";
    String CPN_DCZUCINF = "DC-U-CARD-INF";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String K_OUTPUT_FMT = "DC083";
    String WS_ERR_MSG = " ";
    String WS_CARD_PROD_CD = " ";
    String WS_CARD_CINO = " ";
    String WS_IAACR_SUB_AC = " ";
    char WS_CARD_TYPE = ' ';
    DCZSCLSE_WS_OUTPUT WS_OUTPUT = new DCZSCLSE_WS_OUTPUT();
    int WS_LOS_DT = 0;
    int WS_LOS_DT1 = 0;
    short WS_LOS_DAYS = 0;
    int WS_ISSU_BR = 0;
    short WS_AREA_A = 0;
    short WS_AREA_B = 0;
    int WS_AGREEMENT_CNT = 0;
    int WS_CNT = 0;
    String WS_ACLNK_VIA_AC = " ";
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRACLNK DCRACLNK = new DCRACLNK();
    DCRIAACR DCRIAACR = new DCRIAACR();
    DCCPQPRD DCCDQPRD = new DCCPQPRD();
    DCCUCACJ DCCUCACJ = new DCCUCACJ();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCHLOST DCCHLOST = new DCCHLOST();
    DCCHLOST DCCOLOST = new DCCHLOST();
    DCCHLOST DCCNLOST = new DCCHLOST();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CICMACR CICMACR = new CICMACR();
    CICCAGT CICCAGT = new CICCAGT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    BPCPFPDT BPCPFPDT = new BPCPFPDT();
    DCRCDPRT DCRCDPRT = new DCRCDPRT();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCCILNKU DCCILNKU = new DCCILNKU();
    CICSACR CICSACR = new CICSACR();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    BPRLOSS BPRLOSS = new BPRLOSS();
    BPCSLOSS BPCSLOSS = new BPCSLOSS();
    BPROCAC BPROCAC = new BPROCAC();
    BPCPLOSS BPCPLOSS = new BPCPLOSS();
    CICCACRL CICCACRL = new CICCACRL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DCRCPARM DCRCPARM = new DCRCPARM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRBRARC DCRBRARC = new DCRBRARC();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSCLSE DCCSCLSE;
    public void MP(SCCGWA SCCGWA, DCCSCLSE DCCSCLSE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSCLSE = DCCSCLSE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSCLSE return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_CARD_INFO();
        if (pgmRtn) return;
        B025_CHECK_CARD_ARREARAGE();
        if (pgmRtn) return;
        B030_CARD_STATUS_CHECK();
        if (pgmRtn) return;
        B031_GET_CARD_PROD_INFO();
        if (pgmRtn) return;
        B032_CHECK_CARD_PSW();
        if (pgmRtn) return;
        B060_UPDATE_CARD_STATUS();
        if (pgmRtn) return;
        B065_CANCEL_CITACRL();
        if (pgmRtn) return;
        B075_AGR_NO_STS_UPDATE();
        if (pgmRtn) return;
        B076_UPDATE_OCAC_STS();
        if (pgmRtn) return;
        B080_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        B090_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSCLSE.CARD_NO);
        CEP.TRC(SCCGWA, DCCSCLSE.CLOSE_TYP);
        if (DCCSCLSE.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B025_CHECK_CARD_ARREARAGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFPDT);
        BPCPFPDT.INPUT.CARD_PSBK_NO = DCCSCLSE.CARD_NO;
        S000_CALL_BPZPFPDT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPFPDT.OUTPUT.MAIN_FLG);
        if (BPCPFPDT.OUTPUT.MAIN_FLG == 'Y') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PAY_ARREARAGE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_CARD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DCCSCLSE.CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
    }
    public void B030_CARD_STATUS_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSCLSE.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        WS_ISSU_BR = DCRCDDAT.ISSU_BR;
        if (DCRCDDAT.CARD_STS == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_BE_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_CARD_PROD_CD = DCRCDDAT.PROD_CD;
        WS_CARD_CINO = DCRCDDAT.CARD_HLDR_CINO;
        if (DCCSCLSE.CLOSE_TYP == '1') {
            if (DCRCDDAT.CARD_STS != 'N') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1") 
                || DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (!DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                && DCRCDDAT.CARD_LNK_TYP == '1') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_LOSE_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, DCCSCLSE.LOST_NO);
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            && DCCSCLSE.CLOSE_TYP == '2') {
            if (DCCSCLSE.LOST_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_LOST_MUST_INPUT);
            } else {
                IBS.init(SCCGWA, BPCPLOSS);
                BPCPLOSS.DATA_INFO.LOS_NO = DCCSCLSE.LOST_NO;
                BPCPLOSS.INFO.FUNC = 'I';
                BPCPLOSS.INFO.INDEX_FLG = "1";
                S000_CALL_BPZPLOSS();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.AC);
                if (!BPCPLOSS.DATA_INFO.AC.equalsIgnoreCase(DCCSCLSE.CARD_NO)) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_LOST_NO_NOT_NORMAL);
                }
                WS_LOS_DT = BPCPLOSS.DATA_INFO.LOS_DT;
                IBS.init(SCCGWA, DCRCPARM);
                IBS.init(SCCGWA, BPCPRMR);
                DCRCPARM.KEY.TYP = "DCPRM";
                BPCPRMR.FUNC = ' ';
                DCRCPARM.KEY.CD = SCCGWA.COMM_AREA.TR_BANK;
                IBS.CPY2CLS(SCCGWA, DCRCPARM.KEY.CD, DCRCPARM.KEY.KEY1);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
                BPCPRMR.DAT_PTR = DCRCPARM;
                S000_CALL_BPZPRMR();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(DCCMSG_ERROR_MSG.NOT_FOUND)) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.NOT_FOUND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                DCRCPARM.DATA_TXT.LOS_DAYS = 7;
                CEP.TRC(SCCGWA, DCRCPARM.DATA_TXT.LOS_DAYS);
                WS_LOS_DAYS = DCRCPARM.DATA_TXT.LOS_DAYS;
                CEP.TRC(SCCGWA, WS_LOS_DAYS);
                CEP.TRC(SCCGWA, WS_LOS_DT);
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DAYS = WS_LOS_DAYS;
                SCCCLDT.DATE1 = WS_LOS_DT;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                WS_LOS_DT1 = SCCCLDT.DATE2;
                CEP.TRC(SCCGWA, WS_LOS_DT1);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
                if (SCCGWA.COMM_AREA.AC_DATE < WS_LOS_DT1) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_LOST_DAY_LESS);
                }
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = BPCPLOSS.DATA_INFO.LOS_ORG;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.LOS_ORG);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
                CEP.TRC(SCCGWA, BPCPQORG.BBR);
                CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BBR);
                if (BPCPQORG.BBR != BPCPORUP.DATA_INFO.BBR) {
                    CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_LOST_BR_NOT_MATCH);
                }
            }
        }
        if (DCRCDDAT.CARD_LNK_TYP == '1') {
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
                || DCRCDDAT.CARD_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_ALREADY_FROZEN;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B031_GET_CARD_PROD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = WS_CARD_PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        IBS.init(SCCGWA, DCCDQPRD);
        if (BPCPQPRD.PARM_CODE.trim().length() > 0) {
            DCCDQPRD.VAL.KEY.PROD_CD = BPCPQPRD.PARM_CODE;
        } else {
            DCCDQPRD.VAL.KEY.PROD_CD = WS_CARD_PROD_CD;
        }
        S000_CALL_DCZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCDQPRD.VAL.DATA.ADSC_TYPE);
        CEP.TRC(SCCGWA, DCCDQPRD.VAL.DATA.PSW_FLG);
        CEP.TRC(SCCGWA, DCCDQPRD.VAL.DATA.JOIN_CUS_FLG);
        if (DCCDQPRD.VAL.DATA.ADSC_TYPE == 'C') {
            IBS.init(SCCGWA, DCRBRARC);
            DCRBRARC.KEY.BR_NO = WS_ISSU_BR;
            T000_READ_DCTBRARC();
            if (pgmRtn) return;
            WS_AREA_A = DCRBRARC.AREA_NO;
            CEP.TRC(SCCGWA, WS_ISSU_BR);
            CEP.TRC(SCCGWA, WS_AREA_A);
            IBS.init(SCCGWA, DCRBRARC);
            DCRBRARC.KEY.BR_NO = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_READ_DCTBRARC();
            if (pgmRtn) return;
            WS_AREA_B = DCRBRARC.AREA_NO;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            CEP.TRC(SCCGWA, WS_AREA_B);
            if (WS_AREA_A != WS_AREA_B) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_DIFF_AREA_ERR);
            }
        }
    }
    public void B032_CHECK_CARD_PSW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSCLSE.CARD_PSW);
        if (DCCDQPRD.VAL.DATA.PSW_FLG == 'N') {
            if (DCCSCLSE.CARD_PSW.trim().length() > 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PSW_NOT_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCDQPRD.VAL.DATA.ADSC_TYPE == 'P' 
            && DCCUCINF.CARD_LNK_TYP == '1' 
            && DCCSCLSE.CARD_PSW.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PSW_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCCPCDCK);
        DCCPCDCK.CARD_NO = DCCSCLSE.CARD_NO;
        if (DCCSCLSE.CARD_PSW.trim().length() == 0) {
            if (DCCSCLSE.TRK2_DAT.trim().length() == 0 
                && DCCSCLSE.TRK3_DAT.trim().length() == 0) {
                DCCPCDCK.FUNC_CODE = 'N';
                S000_CALL_DCZPCDCK();
                if (pgmRtn) return;
            } else {
                DCCPCDCK.FUNC_CODE = 'T';
                DCCPCDCK.TRK2_DAT = DCCSCLSE.TRK2_DAT;
                DCCPCDCK.TRK3_DAT = DCCSCLSE.TRK3_DAT;
                S000_CALL_DCZPCDCK();
                if (pgmRtn) return;
            }
        } else {
            if (DCCSCLSE.TRK2_DAT.trim().length() == 0 
                && DCCSCLSE.TRK3_DAT.trim().length() == 0) {
                DCCPCDCK.FUNC_CODE = 'P';
                DCCPCDCK.CARD_PSW = DCCSCLSE.CARD_PSW;
                S000_CALL_DCZPCDCK();
                if (pgmRtn) return;
            } else {
                DCCPCDCK.FUNC_CODE = 'B';
                DCCPCDCK.CARD_PSW = DCCSCLSE.CARD_PSW;
                DCCPCDCK.TRK2_DAT = DCCSCLSE.TRK2_DAT;
                DCCPCDCK.TRK3_DAT = DCCSCLSE.TRK3_DAT;
                S000_CALL_DCZPCDCK();
                if (pgmRtn) return;
            }
        }
    }
    public void B033_DISJ_JOINT_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRACLNK);
        DCRACLNK.KEY.CARD_NO = DCCSCLSE.CARD_NO;
        DCRACLNK.CARD_AC_STATUS = '1';
        T000_READ_DCTACLNK_FIRST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DCRACLNK.VIA_AC);
            WS_ACLNK_VIA_AC = DCRACLNK.VIA_AC;
            IBS.init(SCCGWA, DCRACLNK);
            DCRACLNK.CARD_AC_STATUS = '1';
            WS_CNT = 0;
            T000_STARTBR_DCTACLNK();
            if (pgmRtn) return;
            T000_READ_NEXT_DCTACLNK();
            if (pgmRtn) return;
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                WS_CNT = WS_CNT + 1;
                T000_READ_NEXT_DCTACLNK();
                if (pgmRtn) return;
            }
            T000_ENDBR_DCTACLNK();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_CNT);
            WS_CARD_TYPE = '3';
            if (WS_CNT == 1) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_REMAIN_ONE_JOIN_CARD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_CNT == 2) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_GO_TO_DD_CLOS_AC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                B038_DISJOIN_CARD_AC();
                if (pgmRtn) return;
            }
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_AC_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B034_DISJ_NON_JOINT_CARD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_LNK_TYP);
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_STS);
        CEP.TRC(SCCGWA, DCCDQPRD.VAL.DATA.ADSC_TYPE);
        if (DCRCDDAT.CARD_LNK_TYP == '1' 
            && DCCDQPRD.VAL.DATA.ADSC_TYPE == 'P') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_GOTO_DD_CLOS_AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STS == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_BE_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_LNK_TYP == '1') {
            WS_CARD_TYPE = '1';
        } else {
            WS_CARD_TYPE = '2';
        }
        if (DCRCDDAT.PROD_CD.equalsIgnoreCase("CAD41000")) {
            WS_CARD_TYPE = '4';
        }
        if (DCRCDDAT.PROD_CD.equalsIgnoreCase("CAD52000")) {
            WS_CARD_TYPE = '5';
        }
        if (DCRCDDAT.PROD_CD.equalsIgnoreCase("CAD54000")) {
            WS_CARD_TYPE = '6';
        }
        B039_DIS_NONE_JOIN_CARD_AC();
        if (pgmRtn) return;
    }
    public void B038_DISJOIN_CARD_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRACLNK);
        DCRACLNK.KEY.CARD_NO = DCCSCLSE.CARD_NO;
        DCRACLNK.CARD_AC_STATUS = '1';
        T000_READ_DCTACLNK_FIRST();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.init(SCCGWA, DCCILNKU);
            DCCILNKU.KEY.CARD_NO = DCCSCLSE.CARD_NO;
            DCCILNKU.KEY.VIA_AC = DCRACLNK.VIA_AC;
            DCCILNKU.CARD_TYPE = WS_CARD_TYPE;
            DCCILNKU.CARD_AC_STATUS = '0';
            S000_CALL_DCZILNKU();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_AC_LNK_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B039_DIS_NONE_JOIN_CARD_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRACLNK);
        IBS.init(SCCGWA, DCRIAACR);
        DCRACLNK.KEY.CARD_NO = DCCSCLSE.CARD_NO;
        DCRACLNK.CARD_AC_STATUS = '1';
        T000_READ_DCTACLNK();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            T000_STARTBR_DCTIAACR();
            if (pgmRtn) return;
            T000_READNEXT_DCTIAACR();
            if (pgmRtn) return;
            while ((SCCGWA.COMM_AREA.DBIO_FLG == '0')) {
                IBS.init(SCCGWA, DCCUCACJ);
                DCCUCACJ.FUNC_CODE = 'C';
                if (DCRIAACR.FRM_APP.equalsIgnoreCase("DD")) {
                    DCCUCACJ.AC_TYP = '1';
                }
                if (DCRIAACR.FRM_APP.equalsIgnoreCase("TD")) {
                    DCCUCACJ.AC_TYP = '2';
                }
                DCCUCACJ.AC_NO = DCRIAACR.SUB_AC;
                DCCUCACJ.CARD_LNK_TYP = DCRCDDAT.CARD_LNK_TYP;
                DCCUCACJ.CARD_NO = DCRCDDAT.KEY.CARD_NO;
                if (DCRCDDAT.CARD_LNK_TYP == '1') {
                    DCCUCACJ.PRIM_CARD_NO = DCRCDDAT.KEY.CARD_NO;
                } else {
                    DCCUCACJ.PRIM_CARD_NO = DCRCDDAT.PRIM_CARD_NO;
                }
                S000_CALL_DCZUCACJ();
                if (pgmRtn) return;
                T000_READNEXT_DCTIAACR();
                if (pgmRtn) return;
            }
            T000_ENDBR_DCTIAACR();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_AC_LNK_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B060_UPDATE_CARD_STATUS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSCLSE.CARD_NO;
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        DCRCDDAT.CARD_STS = 'C';
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.CLO_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
        if (DCCSCLSE.CLOSE_TYP == '2') {
            IBS.init(SCCGWA, BPCSLOSS);
            BPCSLOSS.LOS_NO = DCCSCLSE.LOST_NO;
            BPCSLOSS.FUNC = 'U';
            BPCSLOSS.RLOS_ORG = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCSLOSS.RLOS_TLR = "" + SCCGWA.COMM_AREA.TR_DATE;
            JIBS_tmp_int = BPCSLOSS.RLOS_TLR.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) BPCSLOSS.RLOS_TLR = "0" + BPCSLOSS.RLOS_TLR;
            BPCSLOSS.RLOS_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCSLOSS.RLOS_TIME = SCCGWA.COMM_AREA.TR_TIME;
            BPCSLOSS.STS = '6';
            S000_CALL_BPZSLOSS();
            if (pgmRtn) return;
        }
    }
    public void B065_CANCEL_CITACRL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCACRL);
        CICCACRL.DATA.AGR_NO = DCCSCLSE.CARD_NO;
        S000_CALL_CIZCACRL();
        if (pgmRtn) return;
    }
    public void B075_AGR_NO_STS_UPDATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'D';
        CICSACR.DATA.AGR_NO = DCCSCLSE.CARD_NO;
        CICSACR.DATA.ENTY_TYP = '2';
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
    }
    public void B076_UPDATE_OCAC_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'U';
        BPCSOCAC.AC = DCCSCLSE.CARD_NO;
        BPCSOCAC.STS = 'C';
        BPCSOCAC.CLOSE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.CLOSE_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (DCCSCLSE.CLOSE_TYP == '2') {
            BPCSOCAC.CLOSE_AC_STS = 'A';
        }
        BPCSOCAC.CLO_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZSOCAC();
        if (pgmRtn) return;
    }
    public void B080_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_CARD_NO = DCCSCLSE.CARD_NO;
        WS_OUTPUT.WS_LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUTPUT.WS_LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 35;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B090_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCHLOST);
        IBS.init(SCCGWA, DCCNLOST);
        IBS.init(SCCGWA, DCCOLOST);
        DCCNLOST.CARD_NO = DCCSCLSE.CARD_NO;
        DCCNLOST.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCCNLOST.LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        DCCNLOST.LAST_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        R000_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK);
    }
    public void S000_CALL_CIZMACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CIZMACR, CICMACR);
        if (CICMACR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICMACR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CANCEL-ACRL", CICCACRL);
        if (CICCACRL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCACRL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSOCAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZDQPRD, DCCDQPRD);
        if (DCCDQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCDQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPLOSS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-LOSS-INFO", BPCPLOSS);
        CEP.TRC(SCCGWA, BPCPLOSS.RC);
        if (BPCPLOSS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPLOSS.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_BPZSLOSS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-LOSS-INFO", BPCSLOSS);
        CEP.TRC(SCCGWA, BPCSLOSS.RC);
        if (BPCSLOSS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSLOSS.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void R000_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        if (DCCDQPRD.VAL.DATA.ADSC_TYPE == 'C') {
            BPCPNHIS.INFO.TX_TYP_CD = "P210";
        } else {
            BPCPNHIS.INFO.TX_TYP_CD = "P212";
        }
        BPCPNHIS.INFO.TX_RMK = "LOGOUT CARD                ";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "DCCHLOST";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCSCLSE.CARD_NO;
        BPCPNHIS.INFO.CI_NO = DCRCDDAT.CARD_OWN_CINO;
        BPCPNHIS.INFO.AC = DCCSCLSE.CARD_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 42;
        BPCPNHIS.INFO.OLD_DAT_PT = DCCOLOST;
        BPCPNHIS.INFO.NEW_DAT_PT = DCCNLOST;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUCINF, DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCACJ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUCACJ, DCCUCACJ);
        if (DCCUCACJ.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, DCCUCACJ.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCACJ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-FEE-UNCHG-CHK", BPCPFPDT);
        CEP.TRC(SCCGWA, BPCPFPDT.RC);
        if (BPCPFPDT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFPDT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZILNKU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-UPD-ACLNK", DCCILNKU);
        if (DCCILNKU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCILNKU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHK-AC-AGT", CICCAGT);
