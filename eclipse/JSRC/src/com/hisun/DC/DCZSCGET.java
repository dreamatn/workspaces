package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSCGET {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTCITCD_RD;
    DBParm DCTNOCRD_RD;
    DBParm DCTCDORD_RD;
    DBParm DCTCDDAT_RD;
    brParm DCTAPPLC_BR = new brParm();
    DBParm DCTAPPLC_RD;
    DBParm DCTBINPM_RD;
    DBParm DCTINRCD_RD;
    DBParm DCTACLNK_RD;
    DBParm DCTIAMST_RD;
    boolean pgmRtn = false;
    String CPN_DCZUCGET = "DC-U-CARD-GET";
    String CPN_DCZUCCHA = "DC-U-CARD-CHA";
    String CPN_DCZUPSWM = "DC-U-PSW-MAINTAIN";
    String CPN_DCZUCCHB = "DC-U-CARD-CHB";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String CPN_CARD_INF = "DC-U-CARD-INF ";
    String K_OUTPUT_FMT = "DC062";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    long WS_CARD_SEQNO = 0;
    String WS_CARDNO0 = " ";
    String WS_ID_TYP = " ";
    String WS_ID_NO = " ";
    String WS_CI_NM = " ";
    String WS_CI_NO = " ";
    String WS_SOCIAL_CARD_NO = " ";
    String WS_OLD_CARDNO = " ";
    short WS_FLD_NO = 0;
    char WS_PSW_FLG = ' ';
    char WS_S_PSW_FLG = ' ';
    char WS_CARDSELF_FLG = ' ';
    char WS_REC_FLG = ' ';
    char WS_CARD_Z_LOST_FLG = ' ';
    char WS_CITIZEN_CARD_FLG = ' ';
    char WS_TBL_FLAG = ' ';
    char WS_INST_PSW_FLG = ' ';
    DCZSCGET_WS_OUTPUT WS_OUTPUT = new DCZSCGET_WS_OUTPUT();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDORD DCRCDORD = new DCRCDORD();
    DCRINRCD DCRINRCD = new DCRINRCD();
    DCRACLNK DCRACLNK = new DCRACLNK();
    DCRIAMST DCRIAMST = new DCRIAMST();
    DCCHCGET DCCHCGET = new DCCHCGET();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCHCGET DCCOCGET = new DCCHCGET();
    DCCHCGET DCCNCGET = new DCCHCGET();
    DCRBINPM DCRBINPM = new DCRBINPM();
    DCCUCGET DCCUCGET = new DCCUCGET();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    CICCUST CICCUST = new CICCUST();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRNOCRD DCRNOCRD = new DCRNOCRD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPRMM BPCPRMM = new BPCPRMM();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    DCCUCCHB DCCUCCHB = new DCCUCCHB();
    CICQCHDC CICQCHDC = new CICQCHDC();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DCRCITCD DCRCITCD = new DCRCITCD();
    DCCUPWCK DCCUPWCK = new DCCUPWCK();
    DCRAPPLC DCRAPPLC = new DCRAPPLC();
    CICMAGT CICMAGT = new CICMAGT();
    CICQACRI CICQACRI = new CICQACRI();
    CICSAGEN CICSAGEN = new CICSAGEN();
    DCCUPRCD DCCUPRCD = new DCCUPRCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSCGET DCCSCGET;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    public void MP(SCCGWA SCCGWA, DCCSCGET DCCSCGET) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSCGET = DCCSCGET;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSCGET return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DCCSCGET.CARD_NO;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        if (DCCUCINF.CARD_PROD_FLG == 'S' 
            && DCCUCINF.MOVE_FLG == 'Y') {
            WS_CITIZEN_CARD_FLG = 'Y';
        } else {
            WS_CITIZEN_CARD_FLG = 'N';
        }
        if (DCCUCINF.CARD_PROD_FLG == 'S' 
            && DCCUCINF.MOVE_FLG == 'Y' 
            && DCCUCINF.CARD_STS == '2') {
            WS_CITIZEN_CARD_FLG = 'N';
        }
        CEP.TRC(SCCGWA, WS_CITIZEN_CARD_FLG);
        CEP.TRC(SCCGWA, DCCUCINF.PROD_CD);
        if (WS_CITIZEN_CARD_FLG == 'N' 
            && (!DCCUCINF.PROD_CD.equalsIgnoreCase("1203021701") 
            && !DCCUCINF.PROD_CD.equalsIgnoreCase("1203021801"))) {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
        }
        if (WS_CITIZEN_CARD_FLG == 'N') {
            CEP.TRC(SCCGWA, WS_PSW_FLG);
            if (WS_PSW_FLG == 'Y' 
                && DCCSCGET.TXN_PSW1.trim().length() > 0 
                && (!DCCUCINF.PROD_CD.equalsIgnoreCase("1203021701") 
                && !DCCUCINF.PROD_CD.equalsIgnoreCase("1203021801"))) {
                WS_INST_PSW_FLG = 'Y';
                B040_SAVE_PASSWD();
                if (pgmRtn) return;
            }
            B030_CHANGE_CARD();
            if (pgmRtn) return;
            if (DCCUCINF.CARD_PROD_FLG == 'S' 
                && DCCUCINF.MOVE_FLG == 'Y' 
                && DCCUCINF.CARD_STS == '2') {
                B035_CHANGE_DCTCITCD();
                if (pgmRtn) return;
            }
        } else {
            B035_CHANGE_DCTCITCD();
            if (pgmRtn) return;
        }
        if (WS_CITIZEN_CARD_FLG == 'N') {
            B050_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AGENT_FLG);
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            B055_ADD_AGENT_INFO();
            if (pgmRtn) return;
        }
        B060_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSCGET.CARD_NO);
        CEP.TRC(SCCGWA, DCCSCGET.CARD_SEQ);
        CEP.TRC(SCCGWA, DCCSCGET.DOUB_FLG);
        CEP.TRC(SCCGWA, DCCSCGET.CARD_STS);
        if (DCCSCGET.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSCGET.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_STS);
        if (DCRCDDAT.CARD_STS == 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_REP_ACT_CARD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDDAT.CARD_STS == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_BE_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            DCCSCGET.CARD_STS = DCRCDDAT.CARD_STS;
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
        if (DCCSCGET.CARD_SEQ == 0) {
            DCCSCGET.CARD_SEQ = DCRCDDAT.EXC_CARD_TMS;
        }
        if (DCCSCGET.DOUB_FLG == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_DOUB_FLG;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        WS_CI_NO = DCRCDDAT.CARD_HLDR_CINO;
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = WS_CI_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (CICCUST.O_DATA.O_CI_NM.trim().length() > 0) {
            WS_CI_NM = CICCUST.O_DATA.O_CI_NM;
        } else {
            WS_CI_NM = CICCUST.O_DATA.O_CI_ENM;
        }
        WS_ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        WS_ID_NO = CICCUST.O_DATA.O_ID_NO;
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.KEY.CARD_NO = DCCSCGET.CARD_NO;
        DCRCDORD.KEY.EXC_CARD_TMS = DCCSCGET.CARD_SEQ;
        CEP.TRC(SCCGWA, DCCSCGET.CARD_SEQ);
        T000_READ_DCTCDORD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_IN_ORDER;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCRCDORD.CRT_STS);
        if (DCRCDORD.CRT_STS != '3') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_STS_MUST_GOT_CARD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DCRCDORD.CARD_PROD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        IBS.init(SCCGWA, DCRPRDPR);
        IBS.init(SCCGWA, DCCUPRCD);
        DCCUPRCD.TX_TYPE = 'I';
        if (BPCPQPRD.PARM_CODE.trim().length() > 0) {
            DCCUPRCD.DATA.KEY.CD.PARM_CODE = BPCPQPRD.PARM_CODE;
        } else {
            DCCUPRCD.DATA.KEY.CD.PARM_CODE = DCRCDORD.CARD_PROD;
        }
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
        if (DCCUPRCD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUPRCD.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
        }
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT.PSW_FLG);
        if (DCRPRDPR.DATA_TXT.PSW_FLG == 'Y') {
            WS_PSW_FLG = 'Y';
            if (WS_CARDSELF_FLG == 'N' 
                || DCRCDDAT.TRAN_PIN_DAT.trim().length() == 0) {
                if (DCCSCGET.TXN_PSW1.trim().length() == 0) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PSW_MUST_INPUT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (DCCSCGET.TXN_PSW2.trim().length() == 0) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PSW_MUST_INPUT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        } else if (DCRPRDPR.DATA_TXT.PSW_FLG == 'N') {
            WS_PSW_FLG = 'N';
            if (DCCSCGET.TXN_PSW1.trim().length() > 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_PSW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DCCSCGET.TXN_PSW2.trim().length() > 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_PSW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_SOCIAL_CARD() throws IOException,SQLException,Exception {
        if (WS_SOCIAL_CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_LINK_ACT_IN;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = WS_SOCIAL_CARD_NO;
            T000_READ_DCTCDDAT();
            if (pgmRtn) return;
            if (DCRCDDAT.CARD_STS == 'C') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SC_CARD_BE_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPCPQPRD);
            CEP.TRC(SCCGWA, DCRCDDAT.PROD_CD);
            BPCPQPRD.PRDT_CODE = DCRCDDAT.PROD_CD;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
            if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PQPRD_PARM_CD_SPC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, DCRPRDPR);
            IBS.init(SCCGWA, DCCUPRCD);
            DCCUPRCD.TX_TYPE = 'I';
            if (BPCPQPRD.PARM_CODE.trim().length() > 0) {
                DCCUPRCD.DATA.KEY.CD.PARM_CODE = BPCPQPRD.PARM_CODE;
            } else {
                DCCUPRCD.DATA.KEY.CD.PARM_CODE = DCRCDORD.CARD_PROD;
            }
            S000_CALL_DCZUPRCD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCUPRCD.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(DCCMSG_ERROR_MSG.DC_PRD_REC_NOTFND)) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_NOTFND);
            }
            if (DCCUPRCD.RC.RC_CODE == 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA.VAL);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
            }
            WS_S_PSW_FLG = DCRPRDPR.DATA_TXT.PSW_FLG;
            if (WS_S_PSW_FLG == 'Y') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SOCIAL_CARD_NEED_PSW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            B031_CHANGE_SOCILA_CARD();
            if (pgmRtn) return;
        }
    }
    public void B030_CHANGE_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSCGET.CARD_NO;
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCCOCGET);
        DCCOCGET.CARD_NO = DCRCDDAT.KEY.CARD_NO;
        DCCOCGET.CARD_SEQ = DCRCDDAT.EXC_CARD_TMS;
        DCCOCGET.CARD_OWN_CINO = DCRCDDAT.CARD_OWN_CINO;
        DCCOCGET.CARD_HLDR_CINO = DCRCDDAT.CARD_HLDR_CINO;
        DCCOCGET.CARD_STS = DCRCDDAT.CARD_STS;
        DCCOCGET.CARD_STSW = DCRCDDAT.CARD_STSW;
        DCCOCGET.ISSU_DT = DCRCDDAT.ISSU_DT;
        DCCOCGET.LAST_TXN_DT = DCRCDDAT.LAST_TXN_DT;
        DCCOCGET.LAST_DATE = DCRCDDAT.UPDTBL_DATE;
        DCCOCGET.LAST_USER = DCRCDDAT.UPDTBL_TLR;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            DCRCDDAT.LAST_TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRCDDAT.CARD_STS = 'N';
            if (DCCUCINF.FACE_FLG != 'N') {
                DCRCDDAT.DOUBLE_FREE_FLG = DCCSCGET.DOUB_FLG;
                DCRCDDAT.TRAN_PIN_DAT = DCCUPSWM.O_NEW_PSW;
                DCRCDDAT.PSW_TYP = 'N';
            }
            if (WS_INST_PSW_FLG == 'Y') {
                DCRCDDAT.TRAN_PIN_DAT = DCCUPSWM.O_NEW_PSW;
                DCRCDDAT.PSW_TYP = 'N';
            }
            DCRCDDAT.ACT_DT = SCCGWA.COMM_AREA.AC_DATE;
            IBS.init(SCCGWA, DCCNCGET);
            IBS.CLONE(SCCGWA, DCCOCGET, DCCNCGET);
            DCCNCGET.CARD_SEQ = DCRCDDAT.EXC_CARD_TMS;
            DCCNCGET.CARD_STS = DCRCDDAT.CARD_STS;
            DCCOCGET.ISSU_DT = DCRCDDAT.ISSU_DT;
            DCCNCGET.LAST_TXN_DT = DCRCDDAT.LAST_TXN_DT;
            DCCOCGET.LAST_DATE = DCRCDDAT.UPDTBL_DATE;
            DCCOCGET.LAST_USER = DCRCDDAT.UPDTBL_TLR;
            T000_UPDATE_DCTCDDAT();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B031_CHANGE_SOCILA_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = WS_SOCIAL_CARD_NO;
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            DCRCDDAT.LAST_TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRCDDAT.CARD_STS = 'N';
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            if (DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
                JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
                DCRCDDAT.CARD_STSW = DCRCDDAT.CARD_STSW.substring(0, 2 - 1) + "0" + DCRCDDAT.CARD_STSW.substring(2 + 1 - 1);
                R001_INRCD_RECORD();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, DCRACLNK);
            DCRACLNK.KEY.CARD_NO = DCRCDDAT.KEY.CARD_NO;
            T000_READ_DCTACLNK();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                IBS.init(SCCGWA, DCRIAMST);
                DCRIAMST.KEY.VIA_AC = DCRACLNK.VIA_AC;
                T000_READ_DCTIAMST();
                if (pgmRtn) return;
                if (DCRIAMST.STS_WORD == null) DCRIAMST.STS_WORD = "";
                JIBS_tmp_int = DCRIAMST.STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DCRIAMST.STS_WORD += " ";
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
                    && DCRIAMST.STS_WORD.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                    R001_CANCEL_VCH_LOSE_FLG();
                    if (pgmRtn) return;
                }
            }
            T000_UPDATE_DCTCDDAT();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B050_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B035_CHANGE_DCTCITCD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCITCD);
        DCRCITCD.KEY.CARD_NO = DCCSCGET.CARD_NO;
        T000_READUPD_DCTCITCD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            DCRCITCD.CONF_STS = '1';
            DCRCITCD.CONF_DT = SCCGWA.COMM_AREA.AC_DATE;
            DCRCITCD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCITCD.CONF_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DCRCITCD.CONF_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRCITCD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DCTCITCD();
            if (pgmRtn) return;
        }
    }
    public void B040_SAVE_PASSWD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUPSWM);
        DCCUPSWM.FUNC = 'R';
        DCCUPSWM.OLD_AGR_NO = DCCSCGET.CARD_NO;
        DCCUPSWM.AGR_NO = DCCSCGET.CARD_NO;
        if (DCCSCGET.CARD_NO == null) DCCSCGET.CARD_NO = "";
        JIBS_tmp_int = DCCSCGET.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSCGET.CARD_NO += " ";
        DCCUPSWM.AGR_NO_6 = DCCSCGET.CARD_NO.substring(14 - 1, 14 + 6 - 1);
        DCCUPSWM.PSW_TYP = 'T';
        DCCUPSWM.CARD_PSW_NEW = DCCSCGET.TXN_PSW1;
        DCCUPSWM.ID_TYP = WS_ID_TYP;
        DCCUPSWM.ID_NO = WS_ID_NO;
        DCCUPSWM.CI_NM = WS_CI_NM;
        S000_CALL_DCZUPSWM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUPSWM.RC);
        if (DCCUPSWM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUPSWM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCUPSWM.O_NEW_PSW);
    }
    public void B050_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        R000_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void R000_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.TX_RMK = "SINGLE/BATCH CARD GETTING";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "DCCSCGET";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCSCGET.CARD_NO;
        BPCPNHIS.INFO.AC = DCCSCGET.CARD_NO;
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_OWN_CINO);
        BPCPNHIS.INFO.CI_NO = DCRCDDAT.CARD_OWN_CINO;
        BPCPNHIS.INFO.TX_TYP_CD = "PB07";
        CEP.TRC(SCCGWA, "THJ TEST:");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.FMT_ID_LEN = 142;
        BPCPNHIS.INFO.OLD_DAT_PT = DCCOCGET;
        BPCPNHIS.INFO.NEW_DAT_PT = DCCNCGET;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B055_ADD_AGENT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = DCRCDDAT.CARD_OWN_CINO;
        CICSAGEN.OUT_AC = DCCSCGET.CARD_NO;
        CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
        CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        CICSAGEN.AGENT_TP = "04";
        CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
        CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        S000_CALL_CIZSAGEN();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
    }
    public void B060_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_CARD_NO = DCCSCGET.CARD_NO;
        WS_OUTPUT.WS_CARD_STS = DCCSCGET.CARD_STS;
        WS_OUTPUT.WS_DOUBLE_FLG = DCCSCGET.DOUB_FLG;
        CEP.TRC(SCCGWA, WS_CI_NO);
        WS_OUTPUT.WS_HOLDER_IDTYP = WS_ID_TYP;
        WS_OUTPUT.WS_HOLDER_IDNO = WS_ID_NO;
        WS_OUTPUT.WS_HOLDER_NAME = WS_CI_NM;
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_HOLDER_IDTYP);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_HOLDER_IDNO);
        WS_OUTPUT.WS_LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUTPUT.WS_LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, WS_OUTPUT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 366;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CARD_INF, DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, DCCUCINF.RC.RC_CODE);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_DCZUPWCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SIMPLE-PSW-CHECK", DCCUPWCK);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZMAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-AGT", CICMAGT);
        if (CICMAGT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICMAGT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUPSWM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUPSWM, DCCUPSWM);
    }
    public void S000_CALL_DCZUPRCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-MPRD", DCCUPRCD);
        CEP.TRC(SCCGWA, DCCUPRCD.RC);
    }
    public void S000_CALL_BPZUBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-TLR-BV-USE", BPCUBUSE);
    }
    public void S000_CALL_DCZUCCHB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZUCCHB, DCCUCCHB);
        if (DCCUCCHB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCCHB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DCTCITCD() throws IOException,SQLException,Exception {
        DCTCITCD_RD = new DBParm();
        DCTCITCD_RD.TableName = "DCTCITCD";
        DCTCITCD_RD.upd = true;
        IBS.READ(SCCGWA, DCRCITCD, DCTCITCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
            CEP.TRC(SCCGWA, DCRCITCD.KEY.CARD_NO);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTCITCD() throws IOException,SQLException,Exception {
        DCTCITCD_RD = new DBParm();
        DCTCITCD_RD.TableName = "DCTCITCD";
        IBS.REWRITE(SCCGWA, DCRCITCD, DCTCITCD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
            CEP.TRC(SCCGWA, DCRCITCD.KEY.CARD_NO);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTNOCRD() throws IOException,SQLException,Exception {
        DCTNOCRD_RD = new DBParm();
        DCTNOCRD_RD.TableName = "DCTNOCRD";
        DCTNOCRD_RD.col = "NEW_CARD_NO, OLD_CARD_NO, TR_BR, TR_JRNNO, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRNOCRD, DCTNOCRD_RD);
    }
    public void T000_READ_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        IBS.READ(SCCGWA, DCRCDORD, DCTCDORD_RD);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "KIA TESTING READ DTA NORMAL");
            CEP.TRC(SCCGWA, "KIA TESTING");
            CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
            CEP.TRC(SCCGWA, DCRCDDAT.CARD_STS);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "KIA TESTING READ DTA NOT FOUND");
            CEP.TRC(SCCGWA, "KIA TESTING1");
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        CEP.TRC(SCCGWA, "XXXXXXTHJ-1");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_UPDATE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        CEP.TRC(SCCGWA, "XXXXXXTHJ-2");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_DCCAPPLC() throws IOException,SQLException,Exception {
        DCTAPPLC_BR.rp = new DBParm();
        DCTAPPLC_BR.rp.TableName = "DCTAPPLC";
        DCTAPPLC_BR.rp.col = "MPAN , SPAN , STS";
        DCTAPPLC_BR.rp.where = "SPAN = :DCRAPPLC.SPAN";
        IBS.STARTBR(SCCGWA, DCRAPPLC, this, DCTAPPLC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
            CEP.TRC(SCCGWA, "DCCAPPLC NOT FOUND1:");
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCCAPPLC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DCCAPPLC() throws IOException,SQLException,Exception {
        DCTAPPLC_RD = new DBParm();
        DCTAPPLC_RD.TableName = "DCTAPPLC";
        DCTAPPLC_RD.upd = true;
        IBS.READ(SCCGWA, DCRAPPLC, DCTAPPLC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
            CEP.TRC(SCCGWA, "DCCAPPLC NOT FOUND3:");
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCCAPPLC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DCCAPPLC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRAPPLC, this, DCTAPPLC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
            CEP.TRC(SCCGWA, "DCCAPPLC NOT FOUND2:");
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCCAPPLC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DCCAPPLC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTAPPLC_BR);
    }
    public void T000_UPDATE_DCCAPPLC() throws IOException,SQLException,Exception {
        DCTAPPLC_RD = new DBParm();
        DCTAPPLC_RD.TableName = "DCTAPPLC";
        IBS.REWRITE(SCCGWA, DCRAPPLC, DCTAPPLC_RD);
    }
    public void T000_READ_DCTBINPM() throws IOException,SQLException,Exception {
        DCTBINPM_RD = new DBParm();
        DCTBINPM_RD.TableName = "DCTBINPM";
        DCTBINPM_RD.col = "BIN, UNION_SHORT_NAME, UNION_NAME, CHK_DIG_MTH, PIN_MTH, CVV_TYP, CARD_LEN, SEG_NUM, SEG1_LEN, SEG1_RMK, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRBINPM, DCTBINPM_RD);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = DCRPRDPR;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_SEND_MASSAGE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCWRMSG);
        SCCWRMSG.CI_NO = WS_CI_NO;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DCCSCGET.CARD_NO;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_CI_NO.trim().length() > 0) {
            SCCWRMSG.CI_NO = CICQACRI.O_DATA.O_CI_NO;
        }
        CEP.TRC(SCCGWA, SCCWRMSG.CI_NO);
        SCCWRMSG.DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCCWRMSG.JRNNO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = SCCWRMSG.JRNNO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCWRMSG.JRNNO = "0" + SCCWRMSG.JRNNO;
        S000_CALL_SCCWRMSG();
        if (pgmRtn) return;
    }
    public void R001_INRCD_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRINRCD);
        DCRINRCD.KEY.CARD_NO = DCRCDDAT.KEY.CARD_NO;
        DCRINRCD.KEY.TR_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        DCRINRCD.KEY.INCD_TYPE = "12";
        DCRINRCD.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRINRCD.AUT_TLR = SCCGWA.COMM_AREA.SUP1_ID;
        DCRINRCD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRINRCD.CRT_TM = SCCGWA.COMM_AREA.TR_TIME;
        DCRINRCD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRINRCD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRINRCD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTINRCD();
        if (pgmRtn) return;
    }
    public void T000_WRITE_DCTINRCD() throws IOException,SQLException,Exception {
        DCTINRCD_RD = new DBParm();
        DCTINRCD_RD.TableName = "DCTINRCD";
        IBS.WRITE(SCCGWA, DCRINRCD, DCTINRCD_RD);
    }
    public void R001_CANCEL_VCH_LOSE_FLG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAMST);
        DCRIAMST.KEY.VIA_AC = DCRACLNK.VIA_AC;
        T000_READUPD_DCTIAMST();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (DCRIAMST.STS_WORD == null) DCRIAMST.STS_WORD = "";
            JIBS_tmp_int = DCRIAMST.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DCRIAMST.STS_WORD += " ";
            DCRIAMST.STS_WORD = DCRIAMST.STS_WORD.substring(0, 7 - 1) + "0" + DCRIAMST.STS_WORD.substring(7 + 1 - 1);
            DCRIAMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRIAMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_UPDATE_DCTIAMST();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTACLNK() throws IOException,SQLException,Exception {
        DCTACLNK_RD = new DBParm();
        DCTACLNK_RD.TableName = "DCTACLNK";
        DCTACLNK_RD.col = "CARD_NO, VIA_AC, CARD_AC_STATUS";
        DCTACLNK_RD.where = "CARD_NO = :DCRACLNK.KEY.CARD_NO";
        IBS.READ(SCCGWA, DCRACLNK, this, DCTACLNK_RD);
    }
    public void T000_READ_DCTIAMST() throws IOException,SQLException,Exception {
        DCTIAMST_RD = new DBParm();
        DCTIAMST_RD.TableName = "DCTIAMST";
        DCTIAMST_RD.col = "STS_WORD";
        DCTIAMST_RD.where = "VIA_AC = :DCRIAMST.KEY.VIA_AC";
        IBS.READ(SCCGWA, DCRIAMST, this, DCTIAMST_RD);
    }
    public void T000_READUPD_DCTIAMST() throws IOException,SQLException,Exception {
        DCTIAMST_RD = new DBParm();
        DCTIAMST_RD.TableName = "DCTIAMST";
        DCTIAMST_RD.col = "VIA_AC, VIA_FLG, PRD_TYPE, PRD_CODE, AC_STS, STS_WORD, PRT_DTL_FLG, CURR_POS, VAL_NUM, PROC_NUM, TERM, INT_RAT, CCY, CCY_TYPE, OPEN_DATE, OPEN_BR, OPEN_TLR, CLOSE_DATE, CLOSE_BR, CLOSE_TLR, REOPEN_DATE, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTIAMST_RD.where = "VIA_AC = :DCRIAMST.KEY.VIA_AC";
        DCTIAMST_RD.upd = true;
        IBS.READ(SCCGWA, DCRIAMST, this, DCTIAMST_RD);
    }
    public void T000_UPDATE_DCTIAMST() throws IOException,SQLException,Exception {
        DCTIAMST_RD = new DBParm();
        DCTIAMST_RD.TableName = "DCTIAMST";
        DCTIAMST_RD.col = "VIA_FLG, PRD_TYPE, PRD_CODE, AC_STS, STS_WORD, PRT_DTL_FLG, CURR_POS, VAL_NUM, PROC_NUM, TERM, INT_RAT, CCY, CCY_TYPE, OPEN_DATE, OPEN_BR, OPEN_TLR, CLOSE_DATE, CLOSE_BR, CLOSE_TLR, REOPEN_DATE, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTIAMST_RD.where = "VIA_AC = :DCRIAMST.KEY.VIA_AC";
        IBS.REWRITE(SCCGWA, DCRIAMST, this, DCTIAMST_RD);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQCHDC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CHDC", CICQCHDC);
    }
    public void S000_CALL_SCCWRMSG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CALL SCCWRMSG BEGIN");
        IBS.CALLCPN(SCCGWA, "SC-SCZWRMSG-MMSG", SCCWRMSG);
        CEP.TRC(SCCGWA, "CALL SCCWRMSG END");
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.EXCP_FLG != 'Y' 
            && SCCCALL.RET_FLG != 'Y') {
            if ((SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'N' 
                || SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'W') 
                && DCRPRDPR.DATA_TXT.ADSC_TYP == 'P' 
                && WS_CITIZEN_CARD_FLG == 'N') {
            }
        }
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
