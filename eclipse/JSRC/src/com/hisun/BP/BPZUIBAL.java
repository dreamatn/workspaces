package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICMSG_ERROR_MSG;
import com.hisun.AI.AICPQMIB;
import com.hisun.CI.CICQACAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;
import com.hisun.SC.SCRJPRM;

public class BPZUIBAL {
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZUIBAL";
    String K_TBL_FHIST = "BPTFHIST";
    String PGM_SCSSCLDT = "SCSSCLDT";
    int WS_PART_NO = 0;
    double WS_BAL_B = 0;
    double WS_BAL_D = 0;
    double WS_BAL_J = 0;
    double WS_BAL_J1 = 0;
    double WS_BAL_J2 = 0;
    int WS_LAST_BAL_DT = 0;
    int WS_LAST_BAL_DT1 = 0;
    long WS_SQL_JRN_NO = 0;
    short WS_SQL_JRN_SEQ_NO = 0;
    short WS_TP_JRN_SEQ = 0;
    int WS_AC_HASH = 0;
    String WS_H_ACO_AC = " ";
    String WS_ERR_MSG = " ";
    double WS_BAL_T = 0;
    char WS_DRCRFLG = ' ';
    char WS_STS = ' ';
    String WS_ACO_AC = " ";
    String WS_AP_MMO = " ";
    String WS_CCY = " ";
    char WS_CCY_TYP = ' ';
    char WS_AC_FIND_FLG = ' ';
    short WS_CNT_NUM = 0;
    char WS_ADD_FLG = ' ';
    char WS_HIS_FLG = ' ';
    char WS_INIT_FLG = ' ';
    short WS_AC_NUMS = 0;
    BPZUIBAL_WS_AC_INFOS_TABL[] WS_AC_INFOS_TABL = new BPZUIBAL_WS_AC_INFOS_TABL[20];
    char WS_BPTFHIST_FLG = ' ';
    char WS_BPTFHISA_FLG = ' ';
    char WS_TOD_FLG = ' ';
    char WS_JUMP_FLG = ' ';
    char WS_DATA_OVERFLOW_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPRFHISA BPRFHISA = new BPRFHISA();
    AICPQMIB AICPQMIB = new AICPQMIB();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AIRMIB AIRMIB = new AIRMIB();
    BPRFHIS BPRFHIS = new BPRFHIS();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    CICQACAC CICQACAC = new CICQACAC();
    SCCJPMR SCCJPMR = new SCCJPMR();
    SCRJPRM SCRJPRM = new SCRJPRM();
    BPRFHIS BPRFHIS1 = new BPRFHIS();
    BPRFHIS BPRFHIS2 = new BPRFHIS();
    SCCGWA SCCGWA;
    BPCUIBAL BPCUIBAL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCRCWAT SCRCWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public BPZUIBAL() {
        for (int i=0;i<20;i++) WS_AC_INFOS_TABL[i] = new BPZUIBAL_WS_AC_INFOS_TABL();
    }
    public void MP(SCCGWA SCCGWA, BPCUIBAL BPCUIBAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUIBAL = BPCUIBAL;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "INPUT :");
        CEP.TRC(SCCGWA, WS_INIT_FLG);
        CEP.TRC(SCCGWA, BPCUIBAL.INPUT.AC_DT);
        CEP.TRC(SCCGWA, BPCUIBAL.INPUT.JRNNO);
        CEP.TRC(SCCGWA, BPCUIBAL.INPUT.JRN_SEQ);
        CEP.TRC(SCCGWA, BPCUIBAL.INPUT.AC_IN);
        CEP.TRC(SCCGWA, BPCUIBAL.INPUT.CCY);
        CEP.TRC(SCCGWA, BPCUIBAL.INPUT.CCY_TYP);
        CEP.TRC(SCCGWA, BPCUIBAL.INPUT.TX_AMT_IN);
        CEP.TRC(SCCGWA, BPCUIBAL.INPUT.TX_DRCRFLG);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "OUTPUT:");
        CEP.TRC(SCCGWA, BPCUIBAL.OUTPUT.AC);
        CEP.TRC(SCCGWA, BPCUIBAL.OUTPUT.BAL_DIR);
        CEP.TRC(SCCGWA, BPCUIBAL.OUTPUT.TX_AMT);
        CEP.TRC(SCCGWA, BPCUIBAL.OUTPUT.BAL_B);
        CEP.TRC(SCCGWA, BPCUIBAL.OUTPUT.BAL_E);
        CEP.TRC(SCCGWA, "BPZUIBAL return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.init(SCCGWA, BPRFHIS1);
        IBS.init(SCCGWA, BPRFHIS2);
        IBS.init(SCCGWA, BPRFHIST);
        S000_CALL_SCZJPMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCRJPRM.OBLIGATE_FILL);
        if (SCRJPRM.OBLIGATE_FILL == null) SCRJPRM.OBLIGATE_FILL = "";
        JIBS_tmp_int = SCRJPRM.OBLIGATE_FILL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) SCRJPRM.OBLIGATE_FILL += " ";
        WS_HIS_FLG = SCRJPRM.OBLIGATE_FILL.substring(7 - 1, 7 + 1 - 1).charAt(0);
        CEP.TRC(SCCGWA, WS_HIS_FLG);
        if (WS_INIT_FLG == 'Y' 
            && WS_HIS_FLG != '1') {
            B500_IF_AC_IN_OR_NOT();
            if (pgmRtn) return;
        } else {
            WS_INIT_FLG = 'Y';
        }
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCUIBAL.RC);
    }
    public void S000_CALL_SCZJPMR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCJPMR);
        IBS.init(SCCGWA, SCRJPRM);
        SCCJPMR.DAT_PTR = SCRJPRM;
        IBS.CALLCPN(SCCGWA, "SC-JPRM-RD", SCCJPMR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            WS_AC_FIND_FLG = 'N';
            if (WS_AC_FIND_FLG == 'Y') {
                B400_WHEN_FIND_IN_OCCURS();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_AC_INFOS_TABL[WS_CNT_NUM-1]);
            } else {
                B420_WHEN_NFND_IN_OCCURS();
                if (pgmRtn) return;
            }
        } else {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B020_BROWSE_FHSD_RECORD();
            if (pgmRtn) return;
            B040_GET_AC_LSTBAL();
            if (pgmRtn) return;
            B030_GET_AC_AMT();
            if (pgmRtn) return;
            B050_COMPUTE_BAL();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        if (BPCUIBAL.INPUT.AC_DT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_DATE_MUST_INPUT, BPCUIBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCUIBAL.INPUT.JRNNO == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_JRNNO_CANTNOT_ZERO, BPCUIBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCUIBAL.INPUT.AC_DT < SCCGWA.COMM_AREA.AC_DATE) {
            WS_TOD_FLG = 'Y';
        } else {
            WS_TOD_FLG = 'N';
        }
        WS_H_ACO_AC = BPCUIBAL.INPUT.AC_IN;
        R000_GET_PART_NO();
        if (pgmRtn) return;
    }
    public void B020_BROWSE_FHSD_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIST);
        BPRFHIST.KEY.AC_DT = BPCUIBAL.INPUT.AC_DT;
        BPRFHIST.KEY.JRNNO = BPCUIBAL.INPUT.JRNNO;
        BPRFHIST.KEY.JRN_SEQ = BPCUIBAL.INPUT.JRN_SEQ;
        T000_READ_BPTFHIST();
        if (pgmRtn) return;
        BPCUIBAL.OUTPUT.AC = BPRFHIST.KEY.AC;
        BPCUIBAL.OUTPUT.TX_AMT = BPRFHIST.TX_AMT;
        WS_DRCRFLG = BPRFHIST.DRCRFLG;
        WS_STS = BPRFHIST.TX_STS;
        WS_CCY = BPRFHIST.TX_CCY;
        WS_AP_MMO = BPRFHIST.APP_MMO;
        if (BPCUIBAL.OUTPUT.AC.trim().length() == 0) {
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCUIBAL.OUTPUT.AC);
        CEP.TRC(SCCGWA, BPCUIBAL.INPUT.JRNNO);
        CEP.TRC(SCCGWA, BPCUIBAL.INPUT.AC_DT);
        CEP.TRC(SCCGWA, BPCUIBAL.INPUT.JRN_SEQ);
        CEP.TRC(SCCGWA, BPCUIBAL.OUTPUT.TX_AMT);
    }
    public void B020_BROWSE_FHSD_RECORD_CN() throws IOException,SQLException,Exception {
        if (WS_TOD_FLG == 'N') {
            IBS.init(SCCGWA, BPRFHIS);
            BPRFHIS.KEY.AC_DT = BPCUIBAL.INPUT.AC_DT;
            BPRFHIS.KEY.JRNNO = BPCUIBAL.INPUT.JRNNO;
            BPRFHIS.KEY.JRN_SEQ = BPCUIBAL.INPUT.JRN_SEQ;
            T000_READ_BPTFHIS_CN();
            if (pgmRtn) return;
            BPCUIBAL.OUTPUT.AC = BPRFHIS.ACO_AC;
            BPCUIBAL.OUTPUT.TX_AMT = BPRFHIS.TX_AMT;
            WS_DRCRFLG = BPRFHIS.DRCRFLG;
            WS_STS = BPRFHIS.TX_STS;
            WS_CCY = BPRFHIS.TX_CCY;
            WS_CCY_TYP = BPRFHIS.TX_CCY_TYPE;
            WS_AP_MMO = BPRFHIS.APP_MMO;
            WS_ACO_AC = BPRFHIS.ACO_AC;
        } else {
            IBS.init(SCCGWA, BPRFHIST);
            BPRFHIST.KEY.AC_DT = BPCUIBAL.INPUT.AC_DT;
            BPRFHIST.KEY.JRNNO = BPCUIBAL.INPUT.JRNNO;
            BPRFHIST.KEY.JRN_SEQ = BPCUIBAL.INPUT.JRN_SEQ;
            T000_READ_BPTFHIST();
            if (pgmRtn) return;
            BPCUIBAL.OUTPUT.AC = BPRFHIST.ACO_AC;
            BPCUIBAL.OUTPUT.TX_AMT = BPRFHIST.TX_AMT;
            WS_DRCRFLG = BPRFHIST.DRCRFLG;
            WS_STS = BPRFHIST.TX_STS;
            WS_CCY = BPRFHIST.TX_CCY;
            WS_CCY_TYP = BPRFHIST.TX_CCY_TYPE;
            WS_AP_MMO = BPRFHIST.APP_MMO;
            WS_ACO_AC = BPRFHIST.ACO_AC;
        }
        CEP.TRC(SCCGWA, WS_BPTFHIST_FLG);
        if (WS_ACO_AC.trim().length() == 0) {
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCUIBAL.OUTPUT.AC);
        CEP.TRC(SCCGWA, BPCUIBAL.INPUT.JRNNO);
        CEP.TRC(SCCGWA, BPCUIBAL.INPUT.AC_DT);
        CEP.TRC(SCCGWA, BPCUIBAL.INPUT.JRN_SEQ);
        CEP.TRC(SCCGWA, WS_STS);
        CEP.TRC(SCCGWA, WS_CCY);
        CEP.TRC(SCCGWA, WS_CCY_TYP);
        CEP.TRC(SCCGWA, BPCUIBAL.OUTPUT.TX_AMT);
    }
    public void B030_GET_AC_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIST);
        BPRFHIST.KEY.AC_DT = BPCUIBAL.INPUT.AC_DT;
        BPRFHIST.KEY.JRNNO = BPCUIBAL.INPUT.JRNNO;
        BPRFHIST.KEY.AC = BPCUIBAL.OUTPUT.AC;
        BPRFHIST.KEY.JRN_SEQ = BPCUIBAL.INPUT.JRN_SEQ;
        BPRFHIST.TX_CCY = WS_CCY;
        T000_GROUP_BPTFHIST();
        if (pgmRtn) return;
        T030_GROUP_BPTFHIST_JUMP();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCUIBAL.INPUT.AC_DT);
        if (WS_BPTFHIST_FLG == 'N') {
            WS_BAL_D = 0;
            WS_BAL_B = 0;
        }
        CEP.TRC(SCCGWA, WS_BAL_D);
        CEP.TRC(SCCGWA, WS_BAL_B);
    }
    public void B070_CHECK_JUMP_CN() throws IOException,SQLException,Exception {
        WS_JUMP_FLG = 'N';
        if (WS_LAST_BAL_DT > 0) {
            IBS.init(SCCGWA, BPCOCLWD);
            BPCOCLWD.CAL_CODE = BPCRBANK.CALD_BUI;
            BPCOCLWD.DATE1 = WS_LAST_BAL_DT;
            BPCOCLWD.DATE2 = BPCUIBAL.INPUT.AC_DT;
            CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
            CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
            S000_CALL_BPZPCLWD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCOCLWD.DAYS);
            if (BPCOCLWD.DAYS > 2) {
                WS_JUMP_FLG = 'Y';
            }
        } else {
            WS_JUMP_FLG = 'Y';
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "SCBJPRM: BPZPCLWD ERROR ");
            CEP.TRC(SCCGWA, BPCOCLWD.RC.RC_CODE);
            SCCGWA.RETURN_CODE = 8;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_GET_AC_AMT_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIST);
        BPRFHIST.KEY.AC_DT = BPCUIBAL.INPUT.AC_DT;
        BPRFHIST.KEY.JRNNO = BPCUIBAL.INPUT.JRNNO;
        BPRFHIST.KEY.AC = BPCUIBAL.OUTPUT.AC;
        BPRFHIST.ACO_AC = WS_ACO_AC;
        BPRFHIST.KEY.JRN_SEQ = BPCUIBAL.INPUT.JRN_SEQ;
        BPRFHIST.TX_CCY = WS_CCY;
        BPRFHIST.TX_CCY_TYPE = WS_CCY_TYP;
        CEP.TRC(SCCGWA, WS_TOD_FLG);
        if (WS_TOD_FLG == 'N') {
            T000_GROUP_BPTFHIS_CN();
            if (pgmRtn) return;
        } else {
            T000_SELECT_BPTFHIST_BY_JRNNO();
            if (pgmRtn) return;
            if (WS_BPTFHIST_FLG == 'Y') {
                BPCUIBAL.OUTPUT.BAL_E = BPRFHIST.CURR_BAL;
            } else {
                BPCUIBAL.OUTPUT.BAL_E = 0;
            }
        }
        CEP.TRC(SCCGWA, WS_BAL_D);
        CEP.TRC(SCCGWA, WS_JUMP_FLG);
        CEP.TRC(SCCGWA, WS_BAL_D);
        CEP.TRC(SCCGWA, BPCUIBAL.INPUT.AC_DT);
        CEP.TRC(SCCGWA, WS_BAL_D);
        CEP.TRC(SCCGWA, WS_BAL_B);
    }
    public void B040_GET_AC_LSTBAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHISA);
        BPRFHISA.AC_DT = BPCUIBAL.INPUT.AC_DT;
        BPRFHISA.KEY.AC = BPCUIBAL.OUTPUT.AC;
        BPRFHISA.KEY.CCY = WS_CCY;
        CEP.TRC(SCCGWA, BPRFHISA.KEY.CCY);
        T000_STARTBR_BPTFHISA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFHISA.CUR_BAL);
        if (WS_BPTFHISA_FLG == 'Y') {
            WS_BAL_T = BPRFHISA.CUR_BAL;
            WS_LAST_BAL_DT = BPRFHISA.AC_DT;
        }
        if (WS_BPTFHISA_FLG == 'N') {
            WS_BAL_T = 0;
            WS_LAST_BAL_DT = 0;
        }
        CEP.TRC(SCCGWA, WS_BAL_T);
    }
    public void B040_GET_AC_LSTBAL_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHISA);
        BPRFHISA.AC_DT = BPCUIBAL.INPUT.AC_DT;
        BPRFHISA.KEY.AC = BPCUIBAL.OUTPUT.AC;
        BPRFHISA.KEY.CCY = WS_CCY;
        BPRFHISA.KEY.CCY_TYP = WS_CCY_TYP;
        CEP.TRC(SCCGWA, BPRFHISA.KEY.CCY);
        CEP.TRC(SCCGWA, BPRFHISA.KEY.CCY_TYP);
        T000_STARTBR_BPTFHISA_CN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFHISA.CUR_BAL);
        CEP.TRC(SCCGWA, WS_BPTFHISA_FLG);
        if (WS_BPTFHISA_FLG == 'Y') {
            WS_BAL_T = BPRFHISA.CUR_BAL;
            WS_LAST_BAL_DT = BPRFHISA.AC_DT;
        }
        if (WS_BPTFHISA_FLG == 'N') {
            WS_BAL_T = 0;
            WS_LAST_BAL_DT = 0;
        }
        CEP.TRC(SCCGWA, WS_BAL_T);
    }
    public void B050_COMPUTE_BAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUIBAL.OUTPUT.AC);
        CEP.TRC(SCCGWA, WS_AP_MMO);
        WS_DATA_OVERFLOW_FLG = 'Y';
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = BPCUIBAL.INPUT.AC_IN;
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        BPCUIBAL.OUTPUT.BAL_E = WS_BAL_T - WS_BAL_D - WS_BAL_J;
        BPCUIBAL.OUTPUT.BAL_B = WS_BAL_T - WS_BAL_B;
        if (WS_DATA_OVERFLOW_FLG == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATA_OVERFLOW;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B510_MOVE_AC_INFOS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCUIBAL.OUTPUT.BAL_DIR);
        CEP.TRC(SCCGWA, BPCUIBAL.OUTPUT.BAL_E);
        CEP.TRC(SCCGWA, BPCUIBAL.OUTPUT.BAL_B);
    }
    public void B400_WHEN_FIND_IN_OCCURS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_CN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B400");
        CEP.TRC(SCCGWA, WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_DATE);
        CEP.TRC(SCCGWA, WS_BAL_T);
        WS_BAL_T = WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_LST_BAL;
        WS_LAST_BAL_DT = WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_DATE;
        IBS.init(SCCGWA, BPRFHIST);
        B520_CHOOSE_TO_MOVE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TOD_FLG);
        if (WS_TOD_FLG == 'N') {
            if (WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_JRN_NO != BPCUIBAL.INPUT.JRNNO) {
                T000_GROUP_BPTFHIS_CN1();
                if (pgmRtn) return;
            } else {
                T000_GROUP_BPTFHIS_CN2();
                if (pgmRtn) return;
            }
        } else {
            if (WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_JRN_NO != BPCUIBAL.INPUT.JRNNO) {
                T000_GROUP_BPTFHIST_CN1();
                if (pgmRtn) return;
            } else {
                T000_GROUP_BPTFHIST_CN2();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCUIBAL.OUTPUT.AC);
        CEP.TRC(SCCGWA, WS_AP_MMO);
        WS_DATA_OVERFLOW_FLG = 'Y';
        if (WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_TX_DRCRFLG == 'C') {
            WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_TX_AMT = WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_TX_AMT * -1;
        }
        CEP.TRC(SCCGWA, WS_BAL_D);
        CEP.TRC(SCCGWA, WS_BAL_J);
        CEP.TRC(SCCGWA, WS_BAL_T);
        if (WS_ADD_FLG == 'N') {
            WS_BAL_D = WS_BAL_D * -1;
            WS_BAL_J = WS_BAL_J * -1;
            WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_TX_AMT = WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_TX_AMT * -1;
            BPCUIBAL.OUTPUT.BAL_E = WS_BAL_T - WS_BAL_D - WS_BAL_J;
        } else {
            BPCUIBAL.OUTPUT.BAL_E = WS_BAL_T - WS_BAL_D - WS_BAL_J;
        }
        if (WS_DATA_OVERFLOW_FLG == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATA_OVERFLOW;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B510_MOVE_AC_INFOS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCUIBAL.OUTPUT.BAL_E);
    }
    public void B420_WHEN_NFND_IN_OCCURS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_CN();
        if (pgmRtn) return;
        B020_BROWSE_FHSD_RECORD_CN();
        if (pgmRtn) return;
        B040_GET_AC_LSTBAL_CN();
        if (pgmRtn) return;
        B030_GET_AC_AMT_CN();
        if (pgmRtn) return;
        B050_COMPUTE_BAL();
        if (pgmRtn) return;
    }
    public void B500_IF_AC_IN_OR_NOT() throws IOException,SQLException,Exception {
        WS_AC_FIND_FLG = ' ';
        if (BPCUIBAL.INPUT.AC_IN.trim().length() > 0 
            && BPCUIBAL.INPUT.AC_DT != ' ' 
            && BPCUIBAL.INPUT.CCY.trim().length() > 0 
            && BPCUIBAL.INPUT.CCY_TYP != ' ') {
            for (WS_CNT_NUM = 1; WS_CNT_NUM <= 20 
                && WS_AC_FIND_FLG != 'Y'; WS_CNT_NUM += 1) {
                CEP.TRC(SCCGWA, "PERFORM IN");
                CEP.TRC(SCCGWA, WS_CNT_NUM);
                CEP.TRC(SCCGWA, WS_AC_INFOS_TABL[WS_CNT_NUM-1]);
                if (WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC.equalsIgnoreCase(BPCUIBAL.INPUT.AC_IN) 
                    && WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_DATE == BPCUIBAL.INPUT.AC_DT 
                    && WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_CCY.equalsIgnoreCase(BPCUIBAL.INPUT.CCY) 
                    && WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_CCY_TYP == BPCUIBAL.INPUT.CCY_TYP) {
                    WS_AC_FIND_FLG = 'Y';
                } else {
                    WS_AC_FIND_FLG = 'N';
                }
            }
            if (WS_AC_NUMS == 0) {
                WS_AC_FIND_FLG = 'N';
            }
        } else {
            WS_AC_FIND_FLG = 'N';
        }
        CEP.TRC(SCCGWA, "PERFORM AFTER");
        CEP.TRC(SCCGWA, WS_CNT_NUM);
        CEP.TRC(SCCGWA, WS_AC_FIND_FLG);
        if (WS_AC_FIND_FLG == 'Y') {
            WS_CNT_NUM += -1;
        }
    }
    public void B510_MOVE_AC_INFOS() throws IOException,SQLException,Exception {
        WS_AC_FIND_FLG = ' ';
        CEP.TRC(SCCGWA, BPCUIBAL.INPUT.AC_IN);
        CEP.TRC(SCCGWA, BPCUIBAL.INPUT.CCY);
        CEP.TRC(SCCGWA, BPCUIBAL.INPUT.CCY_TYP);
        CEP.TRC(SCCGWA, "STR");
        if (BPCUIBAL.INPUT.AC_IN.trim().length() > 0 
            && BPCUIBAL.INPUT.CCY.trim().length() > 0 
            && BPCUIBAL.INPUT.CCY_TYP != ' ') {
            for (WS_CNT_NUM = 1; WS_CNT_NUM <= 20 
                && WS_AC_FIND_FLG != 'Y'; WS_CNT_NUM += 1) {
                if (WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC.equalsIgnoreCase(BPCUIBAL.INPUT.AC_IN) 
                    && WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_CCY.equalsIgnoreCase(BPCUIBAL.INPUT.CCY) 
                    && WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_CCY_TYP == BPCUIBAL.INPUT.CCY_TYP) {
                    WS_AC_FIND_FLG = 'Y';
                } else {
                    WS_AC_FIND_FLG = 'N';
                }
            }
            if (WS_AC_NUMS == 0) {
                WS_AC_FIND_FLG = 'N';
            }
        } else {
            WS_AC_FIND_FLG = 'N';
        }
        CEP.TRC(SCCGWA, WS_AC_FIND_FLG);
        if (WS_AC_FIND_FLG == 'Y') {
            WS_CNT_NUM += -1;
            if (BPCUIBAL.INPUT.AC_IN.trim().length() == 0) {
                WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC = BPRFHIST.KEY.AC;
            } else {
                WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC = BPCUIBAL.INPUT.AC_IN;
            }
            WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_CCY = BPCUIBAL.INPUT.CCY;
            WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_CCY_TYP = BPCUIBAL.INPUT.CCY_TYP;
            WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_DATE = BPCUIBAL.INPUT.AC_DT;
            WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_LST_BAL = BPCUIBAL.OUTPUT.BAL_E;
            WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_JRN_NO = BPCUIBAL.INPUT.JRNNO;
            WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_JRN_SEQ_NO = BPCUIBAL.INPUT.JRN_SEQ;
            WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_TX_AMT = BPCUIBAL.INPUT.TX_AMT_IN;
            WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_TX_DRCRFLG = BPCUIBAL.INPUT.TX_DRCRFLG;
        } else {
            WS_AC_NUMS += 1;
            if (WS_AC_NUMS > 20) {
                WS_AC_NUMS = 20;
                CEP.TRC(SCCGWA, "WS-AC-NUMS COME TO MAX");
                CEP.TRC(SCCGWA, "ERR-PARPH: B510-MOVE-AC-INFOS");
                CEP.TRC(SCCGWA, "CALCULATE ONE MORE TIME");
                IBS.init(SCCGWA, WS_AC_INFOS_TABL[WS_AC_NUMS-1]);
            }
            if (BPCUIBAL.OUTPUT.AC.trim().length() == 0) {
                WS_AC_INFOS_TABL[WS_AC_NUMS-1].WS_AC = BPRFHIST.KEY.AC;
            } else {
                WS_AC_INFOS_TABL[WS_AC_NUMS-1].WS_AC = BPCUIBAL.INPUT.AC_IN;
            }
            WS_AC_INFOS_TABL[WS_AC_NUMS-1].WS_AC_CCY = BPCUIBAL.INPUT.CCY;
            WS_AC_INFOS_TABL[WS_AC_NUMS-1].WS_AC_CCY_TYP = BPCUIBAL.INPUT.CCY_TYP;
            WS_AC_INFOS_TABL[WS_AC_NUMS-1].WS_AC_DATE = BPCUIBAL.INPUT.AC_DT;
            WS_AC_INFOS_TABL[WS_AC_NUMS-1].WS_AC_LST_BAL = BPCUIBAL.OUTPUT.BAL_E;
            WS_AC_INFOS_TABL[WS_AC_NUMS-1].WS_AC_JRN_NO = BPCUIBAL.INPUT.JRNNO;
            WS_AC_INFOS_TABL[WS_AC_NUMS-1].WS_AC_JRN_SEQ_NO = BPCUIBAL.INPUT.JRN_SEQ;
            WS_AC_INFOS_TABL[WS_AC_NUMS-1].WS_AC_TX_AMT = BPCUIBAL.INPUT.TX_AMT_IN;
            WS_AC_INFOS_TABL[WS_AC_NUMS-1].WS_AC_TX_DRCRFLG = BPCUIBAL.INPUT.TX_DRCRFLG;
        }
        CEP.TRC(SCCGWA, "B510-END");
    }
    public void B520_CHOOSE_TO_MOVE() throws IOException,SQLException,Exception {
        if (WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_DATE == BPCUIBAL.INPUT.AC_DT 
                && WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_JRN_NO < BPCUIBAL.INPUT.JRNNO) {
            BPRFHIST.KEY.JRNNO = BPCUIBAL.INPUT.JRNNO;
            BPRFHIST.KEY.JRN_SEQ = BPCUIBAL.INPUT.JRN_SEQ;
            WS_SQL_JRN_NO = WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_JRN_NO;
            WS_TP_JRN_SEQ = WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_JRN_SEQ_NO;
            WS_ADD_FLG = 'Y';
            CEP.TRC(SCCGWA, "COND-1");
        } else if (WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_DATE == BPCUIBAL.INPUT.AC_DT 
                && WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_JRN_NO > BPCUIBAL.INPUT.JRNNO) {
            BPRFHIST.KEY.JRNNO = WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_JRN_NO;
            BPRFHIST.KEY.JRN_SEQ = WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_JRN_SEQ_NO;
            WS_SQL_JRN_NO = BPCUIBAL.INPUT.JRNNO;
            WS_TP_JRN_SEQ = BPCUIBAL.INPUT.JRN_SEQ;
            WS_ADD_FLG = 'N';
            CEP.TRC(SCCGWA, "COND-2");
        } else if (WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_DATE == BPCUIBAL.INPUT.AC_DT 
                && WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_JRN_NO == BPCUIBAL.INPUT.JRNNO 
                && WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_JRN_SEQ_NO < BPCUIBAL.INPUT.JRN_SEQ) {
            BPRFHIST.KEY.JRNNO = WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_JRN_NO;
            BPRFHIST.KEY.JRN_SEQ = BPCUIBAL.INPUT.JRN_SEQ;
            WS_SQL_JRN_NO = BPCUIBAL.INPUT.JRNNO;
            WS_TP_JRN_SEQ = WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_JRN_SEQ_NO;
            WS_ADD_FLG = 'Y';
            CEP.TRC(SCCGWA, "COND-3");
        } else if (WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_DATE == BPCUIBAL.INPUT.AC_DT 
                && WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_JRN_NO == BPCUIBAL.INPUT.JRNNO 
                && WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_JRN_SEQ_NO > BPCUIBAL.INPUT.JRN_SEQ) {
            BPRFHIST.KEY.JRNNO = WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_JRN_NO;
            BPRFHIST.KEY.JRN_SEQ = WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_JRN_SEQ_NO;
            WS_SQL_JRN_NO = BPCUIBAL.INPUT.JRNNO;
            WS_TP_JRN_SEQ = BPCUIBAL.INPUT.JRN_SEQ;
            WS_ADD_FLG = 'N';
            CEP.TRC(SCCGWA, "COND-4");
        } else {
            CEP.TRC(SCCGWA, "SQL WHERE CLUASE ERROR");
            B420_WHEN_NFND_IN_OCCURS();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_ADD_FLG);
        BPRFHIST.KEY.AC_DT = WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_DATE;
        BPRFHIST.KEY.AC = WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC;
        BPRFHIST.TX_CCY = WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_CCY;
        BPRFHIST.TX_CCY_TYPE = WS_AC_INFOS_TABL[WS_CNT_NUM-1].WS_AC_CCY_TYP;
    }
    public void B530_GROUP_BPTFHIST_JUMP_CN() throws IOException,SQLException,Exception {
        WS_BAL_J1 = 0;
        WS_BAL_J2 = 0;
        if (WS_HIS_FLG == '1') {
            if (BPCUIBAL.INPUT.AC_DT > SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE 
                    && SCRCWA.JRN_IN_USE == '2') {
                T060_GROUP_BPTFHIST_JUMP_CN();
                if (pgmRtn) return;
            } else if (BPCUIBAL.INPUT.AC_DT > SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE 
                    && SCRCWA.JRN_IN_USE == '1') {
                T070_GROUP_BPTFHIST_JUMP_CN();
                if (pgmRtn) return;
            }
            if (BPCUIBAL.INPUT.AC_DT >= SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE) {
                T080_GROUP_BPTFHIST_JUMP_CN();
                if (pgmRtn) return;
            } else if (BPCUIBAL.INPUT.AC_DT < SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE) {
                T090_GROUP_BPTFHIST_JUMP_CN();
                if (pgmRtn) return;
            }
        } else {
            T090_GROUP_BPTFHIST_JUMP_CN();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_BAL_J);
        WS_BAL_J = WS_BAL_J + WS_BAL_J1 + WS_BAL_J2;
        CEP.TRC(SCCGWA, WS_LAST_BAL_DT);
        CEP.TRC(SCCGWA, BPRFHIST.KEY.AC_DT);
        CEP.TRC(SCCGWA, WS_BAL_J1);
        CEP.TRC(SCCGWA, WS_BAL_J2);
        CEP.TRC(SCCGWA, WS_BAL_J);
        CEP.TRC(SCCGWA, WS_BAL_D);
        CEP.TRC(SCCGWA, WS_BAL_T);
    }
    public void B540_GROUP_BPTFHIST_CN() throws IOException,SQLException,Exception {
        if (WS_HIS_FLG == '1') {
            if (BPCUIBAL.INPUT.AC_DT < SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE) {
                T000_GROUP_BPTFHIST_CN();
                if (pgmRtn) return;
            } else if (BPCUIBAL.INPUT.AC_DT == SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE 
                    && SCRCWA.JRN_IN_USE == '2') {
                T040_GROUP_BPTFHIST_JUMP_CN();
                if (pgmRtn) return;
            } else if (BPCUIBAL.INPUT.AC_DT == SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE 
                    && SCRCWA.JRN_IN_USE == '1') {
                T050_GROUP_BPTFHIST_JUMP_CN();
                if (pgmRtn) return;
            } else if (BPCUIBAL.INPUT.AC_DT > SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE) {
                T000_GROUP_BPTFHIST_CN();
                if (pgmRtn) return;
            } else {
                T000_GROUP_BPTFHIST_CN();
                if (pgmRtn) return;
            } else {
                T000_GROUP_BPTFHIST_CN();
                if (pgmRtn) return;
            }
    }
    public void B550_CAL_BAL_D_AMT() throws IOException,SQLException,Exception {
        if (BPCUIBAL.OUTPUT.TX_AMT != 0) {
            WS_BAL_D = BPCUIBAL.OUTPUT.TX_AMT;
            if (WS_DRCRFLG == 'C') {
                WS_BAL_D = WS_BAL_D * -1;
            }
            if (WS_DRCRFLG == 'D') {
                WS_BAL_D = WS_BAL_D * 1;
            }
        } else {
            if (BPCUIBAL.INPUT.TX_AMT_IN != 0) {
                WS_BAL_D = BPCUIBAL.INPUT.TX_AMT_IN;
                if (BPCUIBAL.INPUT.TX_DRCRFLG == 'C') {
                    WS_BAL_D = WS_BAL_D * -1;
                }
                if (BPCUIBAL.INPUT.TX_DRCRFLG == 'D') {
                    WS_BAL_D = WS_BAL_D * 1;
                }
            } else {
                WS_BAL_D = 0;
            }
        }
    }
    public void T000_READ_BPTFHIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUIBAL.INPUT.AC_DT);
        if (WS_HIS_FLG == '1' 
            && BPCUIBAL.INPUT.AC_DT == SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE) {
            if (SCRCWA.JRN_IN_USE == '2') {
                CEP.TRC(SCCGWA, "COMES-TO-HERE-1");
                BPTFHIS1_RD = new DBParm();
                BPTFHIS1_RD.TableName = "BPTFHIS1";
                BPTFHIS1_RD.where = "AC_DT = :BPRFHIST.KEY.AC_DT "
                    + "AND JRNNO = :BPRFHIST.KEY.JRNNO "
                    + "AND JRN_SEQ = :BPRFHIST.KEY.JRN_SEQ";
                BPTFHIS1_RD.fst = true;
                IBS.READ(SCCGWA, BPRFHIS1, this, BPTFHIS1_RD);
