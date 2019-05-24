package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSEXT {
    int JIBS_tmp_int;
    DBParm LNTRATN_RD;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTRATH_RD;
    DBParm LNTRATL_RD;
    DBParm LNTEXTN_RD;
    DBParm LNTPPRP_RD;
    DBParm BPTNHIST_RD;
    boolean pgmRtn = false;
    String CPN_SCSSCKDT = "SCSSCKDT";
    String K_HIS_RMKS = "CONTRACT EXTENSION";
    char K_EXTN_STATUS = '1';
    char K_DEL_STATUS = '4';
    char K_EXTN_EXT_FLG_1 = '1';
    char K_EXTN_EXT_FLG_2 = '2';
    char K_EXTN_CONTRACT_ATTR = 'D';
    String K_PPMQ_PROD_CLS_ADV = "P004";
    String K_PPMQ_PROD_SYR = "P023";
    String K_PPMQ_PROD_XBD = "P010";
    String K_PPMQ_PROD_FUND = "P008";
    char K_CKPD_INQ = '0';
    LNZSEXT_WS_ERR_MSG WS_ERR_MSG = new LNZSEXT_WS_ERR_MSG();
    char WS_EXIST_FWDH_FLAG = ' ';
    char WS_FUNC_CODE = ' ';
    char WS_FWDH_FOUND_FLG = ' ';
    char WS_BALH_FOUND_FLG = ' ';
    char WS_BALZ_FOUND_FLG = ' ';
    int WS_CNT = 0;
    String WS_CTA_NO = " ";
    int WS_MAT_DATE = 0;
    int WS_DEL_DATE = 0;
    int WS_VAL_DATE2 = 0;
    String WS_TR_CODE = " ";
    double WS_B_AMT = 0;
    char WS_PAY_YSX = ' ';
    char WS_EXT_FLG = ' ';
    char WS_DEL_EXT_FLG = ' ';
    int WS_VAL_DATE = 0;
    String WS_FATHER_CONT = " ";
    char WS_CTA_TYP = ' ';
    char WS_STATUS = ' ';
    char WS_DEL_STATUS = ' ';
    short WS_CUR_TERM = 0;
    short WS_CAL_TERM = 0;
    int WS_CAL_DUE_DT = 0;
    char WS_READNEXT_FLG = ' ';
    double WS_EFF_RAT_I = 0;
    double WS_EFF_RAT_O = 0;
    double WS_EFF_RAT_L = 0;
    double WS_EFF_RAT_P = 0;
    char WS_INT_TYP = ' ';
    int WS_RATL_ACTV_DT = 0;
    int WS_RATN_ACTV_DT = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    LNCUEXT LNCUEXT = new LNCUEXT();
    LNRICTL LNRICTL = new LNRICTL();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCHUEXT LNCHUEXO = new LNCHUEXT();
    LNCHUEXT LNCHUEXN = new LNCHUEXT();
    LNCURAT LNCURAT = new LNCURAT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNREXTN LNREXTN = new LNREXTN();
    LNCREXTN LNCREXTN = new LNCREXTN();
    LNRRATL LNRRATL = new LNRRATL();
    LNCRRATL LNCRRATL = new LNCRRATL();
    LNRRATN LNRRATN = new LNRRATN();
    LNCRRATN LNCRRATN = new LNCRRATN();
    LNCRATHM LNCRATHM = new LNCRATHM();
    LNRRATH LNRRATH = new LNRRATH();
    LNCRATLM LNCRATLM = new LNCRATLM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRNHIST BPRNHIST = new BPRNHIST();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCFTLAM BPCFTLAM = new BPCFTLAM();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    LNCICIQ LNCICIQ = new LNCICIQ();
    LNRCMMT LNRCMMT = new LNRCMMT();
    LNCRCMMT LNCRCMMT = new LNCRCMMT();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    LNRPPRP LNRPPRP = new LNRPPRP();
    LNCSCKPD LNCSCKPD = new LNCSCKPD();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNRBALH LNRBALH = new LNRBALH();
    LNCBALHM LNCBALHM = new LNCBALHM();
    LNRBALZ LNRBALZ = new LNRBALZ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    LNCSEXT LNCSEXT;
    SCCBATH SCCBATH;
    public void MP(SCCGWA SCCGWA, LNCSEXT LNCSEXT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSEXT = LNCSEXT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSEXT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
            CEP.TRC(SCCGWA, SCCBATH.PROC_NAME);
            CEP.TRC(SCCGWA, SCCBATH.PGM_NAME);
        }
        WS_FUNC_CODE = LNCSEXT.FUNC;
        LNCSEXT.RC.RC_APP = "LN";
        LNCSEXT.RC.RC_RTNCODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSEXT.VAL_DATE);
        CEP.TRC(SCCGWA, WS_FUNC_CODE);
        CEP.TRC(SCCGWA, LNCSEXT.VAL_DATE);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (WS_FUNC_CODE == 'A') {
                B010_ADD_RECORD_PROC();
                if (pgmRtn) return;
                if (WS_MAT_DATE == LNCSEXT.VAL_DATE) {
                    B010_ADD_BALH_PROC();
                    if (pgmRtn) return;
                }
            } else if (WS_FUNC_CODE == 'M') {
                B020_MODIFY_RECORD_PROC();
                if (pgmRtn) return;
            } else if (WS_FUNC_CODE == 'D') {
                B030_DELETE_RECORD_PROC();
                if (pgmRtn) return;
            } else if (WS_FUNC_CODE == 'I') {
                B040_QUERY_RECORD_PROC();
                if (pgmRtn) return;
            }
        } else {
            B010_UEXT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B000_CHECK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSEXT.CTA_TYP);
        CEP.TRC(SCCGWA, LNCSEXT.B_AMT);
        if (LNCSEXT.CTA_TYP != 'D') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CTA_TYP2, LNCSEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCSEXT.EE_DATE);
        if (LNCSEXT.EE_DATE != 0 
            && LNCSEXT.FUNC != 'I' 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && LNCSEXT.EE_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_EE_DATE, LNCSEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((LNCSEXT.FUNC == 'A' 
            || LNCSEXT.FUNC == 'M')) {
            if (LNCSEXT.FUNC != 'I' 
                && SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
                && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                B080_CHECK_ICTL_STS();
                if (pgmRtn) return;
            }
            WS_CTA_NO = LNCSEXT.CTA_NO;
            S000_INQUIRE_ICTL();
            if (pgmRtn) return;
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1));
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                && LNCSEXT.FUNC != 'I') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SETTLE_STS, LNCSEXT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(8 - 1, 8 + 1 - 1));
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            if ((LNRICTL.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || LNRICTL.CTL_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
                || LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1") 
                || LNRICTL.CTL_STSW.substring(37 - 1, 37 + 1 - 1).equalsIgnoreCase("1") 
                || LNRICTL.CTL_STSW.substring(38 - 1, 38 + 1 - 1).equalsIgnoreCase("1")) 
                && LNCSEXT.FUNC != 'I') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_VERIFICATION_NO, LNCSEXT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            S000_INQUIRE_CONT();
            if (pgmRtn) return;
            WS_FATHER_CONT = LNRCONT.FATHER_CONTRACT;
            WS_MAT_DATE = LNRCONT.MAT_DATE;
            LNCSEXT.OE_DATE = LNRCONT.MAT_DATE;
            if (!LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLDD")) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CONT_TYPE, LNCSEXT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, LNRCONT.MAT_DATE);
            if (LNCSEXT.EE_DATE != 0 
                && SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
                && LNCSEXT.EE_DATE == LNRCONT.MAT_DATE) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_EE_DATE_ERR, LNCSEXT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            B060_GET_INFO_PROC();
            if (pgmRtn) return;
            if (WS_MAT_DATE > LNCSEXT.EE_DATE) {
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                if (LNRICTL.CTL_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERROR_EXTN_P, LNCSEXT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (WS_MAT_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERROR_EXTN_CUT_D, LNCSEXT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
            if (LNCSEXT.EXT_AMT == 0) {
                LNCSEXT.EXT_AMT = LNCCLNQ.DATA.TOT_BAL;
            }
            CEP.TRC(SCCGWA, LNCCLNQ.DATA.TOT_BAL);
            CEP.TRC(SCCGWA, LNCSEXT.B_AMT);
            CEP.TRC(SCCGWA, LNCSEXT.EXT_AMT);
            WS_B_AMT = LNCSEXT.B_AMT;
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
                && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                IBS.init(SCCGWA, LNRPPRP);
                LNRPPRP.KEY.CONTRACT_NO = WS_CTA_NO;
                LNRPPRP.STATUS = '1';
                T000_READ_LNTPPRP();
                if (pgmRtn) return;
                IBS.init(SCCGWA, LNCAPRDM);
                LNCAPRDM.FUNC = '3';
                LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = WS_CTA_NO;
                S000_CALL_LNZAPRDM();
                if (pgmRtn) return;
                IBS.init(SCCGWA, LNCSCKPD);
                LNCSCKPD.FUNC = K_CKPD_INQ;
                LNCSCKPD.PROD_CD = LNRCONT.PROD_CD;
                S000_CALL_LNZSCKPD();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_FUNC_CODE);
                if (WS_FUNC_CODE == 'A' 
                    && LNCAPRDM.REC_DATA.PAY_MTH == '0') {
                    WS_PAY_YSX = LNCAPRDM.REC_DATA.PAY_MTH;
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAY_INTEREST, LNCSEXT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (LNCSCKPD.PROD_CLS.equalsIgnoreCase(K_PPMQ_PROD_CLS_ADV)) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PROD_CLSF, LNCSEXT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                if (LNRICTL.CTL_STSW.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1")) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DRAW_TYPE, LNCSEXT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B010_READ_LNTRATN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATN);
        IBS.init(SCCGWA, LNCRRATN);
        LNRRATN.KEY.CONTRACT_NO = LNCSEXT.CTA_NO;
        LNRRATN.KEY.ACTV_DT = LNCSEXT.VAL_DATE;
        LNCRRATN.FUNC = 'I';
        CEP.TRC(SCCGWA, LNCSEXT.CTA_NO);
        CEP.TRC(SCCGWA, LNCSEXT.VAL_DATE);
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        LNTRATN_RD.where = "CONTRACT_NO = :LNRRATN.KEY.CONTRACT_NO "
            + "AND ACTV_DT <= :LNRRATN.KEY.ACTV_DT";
        LNTRATN_RD.fst = true;
        LNTRATN_RD.order = "ACTV_DT DESC";
        IBS.READ(SCCGWA, LNRRATN, this, LNTRATN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATN_NOTFND, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNRRATN.ALL_IN_RATE);
        WS_EFF_RAT_I = LNRRATN.ALL_IN_RATE;
        WS_RATN_ACTV_DT = LNRRATN.KEY.ACTV_DT;
    }
    public void B010_READUPD_LNTRATN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATN);
        IBS.init(SCCGWA, LNCRRATN);
        LNRRATN.KEY.CONTRACT_NO = LNCSEXT.CTA_NO;
        LNRRATN.KEY.ACTV_DT = WS_RATN_ACTV_DT;
        LNCRRATN.FUNC = 'R';
        CEP.TRC(SCCGWA, LNCSEXT.CTA_NO);
        CEP.TRC(SCCGWA, LNCSEXT.VAL_DATE);
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        LNTRATN_RD.upd = true;
        LNTRATN_RD.where = "CONTRACT_NO = :LNRRATN.KEY.CONTRACT_NO "
            + "AND ACTV_DT = :LNRRATN.KEY.ACTV_DT";
        IBS.READ(SCCGWA, LNRRATN, this, LNTRATN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_RATN_NOTFND, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCRRATN.RC.RC_CODE);
    }
    public void B010_UPDATE_LNTRATN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATN);
        IBS.init(SCCGWA, LNCRRATN);
        LNRRATN.KEY.CONTRACT_NO = LNCSEXT.CTA_NO;
        LNRRATN.KEY.ACTV_DT = LNCSEXT.VAL_DATE;
        LNRRATN.ALL_IN_RATE = LNCSEXT.ALL_IN_R;
        LNRRATN.INT_RATE_TYPE1 = LNCSEXT.IRAT_TYP;
        LNRRATN.INT_RATE_CLAS1 = LNCSEXT.RAT_PERD;
        LNRRATN.BASE_RATE1 = LNCSEXT.RAT_VARI;
        LNRRATN.RATE_VARIATION1 = LNCSEXT.RAT_PECT;
        LNRRATN.RATE_PECT1 = LNCSEXT.RAT_REF;
        LNCRRATN.FUNC = 'U';
        CEP.TRC(SCCGWA, LNCSEXT.CTA_NO);
        CEP.TRC(SCCGWA, LNCSEXT.VAL_DATE);
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        IBS.REWRITE(SCCGWA, LNRRATN, LNTRATN_RD);
        CEP.TRC(SCCGWA, LNCRRATN.RC.RC_CODE);
    }
    public void B010_READ_LNTRATL_O_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATL);
        LNRRATL.KEY.CONTRACT_NO = LNCSEXT.CTA_NO;
        LNRRATL.KEY.OVD_KND = 'O';
        LNRRATL.KEY.ACTV_DT = LNCSEXT.VAL_DATE;
        CEP.TRC(SCCGWA, LNRRATL.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRATL.KEY.OVD_KND);
        CEP.TRC(SCCGWA, LNRRATL.KEY.ACTV_DT);
        S000_CALL_LNZRRATL();
        if (pgmRtn) return;
        WS_EFF_RAT_O = LNRRATL.EFF_RAT;
        WS_RATL_ACTV_DT = LNRRATL.KEY.ACTV_DT;
        CEP.TRC(SCCGWA, WS_EFF_RAT_O);
    }
    public void B010_READ_LNTRATL_L_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATL);
        LNRRATL.KEY.CONTRACT_NO = LNCSEXT.CTA_NO;
        LNRRATL.KEY.OVD_KND = 'L';
        LNRRATL.KEY.ACTV_DT = LNCSEXT.VAL_DATE;
        CEP.TRC(SCCGWA, "COMPOUND INT");
        CEP.TRC(SCCGWA, LNRRATL.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRATL.KEY.OVD_KND);
        CEP.TRC(SCCGWA, LNRRATL.KEY.ACTV_DT);
        S000_CALL_LNZRRATL();
        if (pgmRtn) return;
        WS_EFF_RAT_L = LNRRATL.EFF_RAT;
        CEP.TRC(SCCGWA, WS_EFF_RAT_L);
    }
    public void B010_READ_LNTRATL_P_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATL);
        LNRRATL.KEY.CONTRACT_NO = LNCSEXT.CTA_NO;
        LNRRATL.KEY.OVD_KND = 'P';
        LNRRATL.KEY.ACTV_DT = LNCSEXT.VAL_DATE;
        CEP.TRC(SCCGWA, "DEVERT PENALTY INT");
        CEP.TRC(SCCGWA, LNRRATL.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRATL.KEY.OVD_KND);
        CEP.TRC(SCCGWA, LNRRATL.KEY.ACTV_DT);
        S000_CALL_LNZRRATL();
        if (pgmRtn) return;
        WS_EFF_RAT_P = LNRRATL.EFF_RAT;
        CEP.TRC(SCCGWA, WS_EFF_RAT_P);
    }
    public void B010_READUPD_LNTRATL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATL);
        LNRRATL.KEY.CONTRACT_NO = LNCSEXT.CTA_NO;
        LNRRATL.KEY.OVD_KND = WS_INT_TYP;
        LNRRATL.KEY.ACTV_DT = WS_RATL_ACTV_DT;
        LNCRRATL.FUNC = 'U';
        S000_READUPD_LNTRATL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRRATL.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRATL.EFF_RAT);
    }
    public void B010_UPD_O_LNTRATL_PROC() throws IOException,SQLException,Exception {
        LNRRATL.EFF_RAT = LNCSEXT.PEN_IRAT;
        LNRRATL.INT_CHRG_MTH = LNCSEXT.PEN_RRAT;
        LNRRATL.RATE_TYPE = LNCSEXT.PEN_RATT;
        LNRRATL.RATE_CLAS = LNCSEXT.PEN_RATC;
        LNRRATL.DIF_IRAT_PER = LNCSEXT.PEN_PCT;
        LNRRATL.DIF_IRAT_PNT = LNCSEXT.PEN_SPR;
        LNRRATL.VARIATION_METHOD = LNCSEXT.PEN_TYP;
        LNCRRATL.FUNC = 'U';
        CEP.TRC(SCCGWA, LNRRATL.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRATL.KEY.OVD_KND);
        CEP.TRC(SCCGWA, LNRRATL.KEY.ACTV_DT);
        S000_UPD_LNTRATL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRRATL.EFF_RAT);
        CEP.TRC(SCCGWA, LNRRATL.RATE_CLAS);
    }
    public void B010_UPD_L_LNTRATL_PROC() throws IOException,SQLException,Exception {
        LNRRATL.EFF_RAT = LNCSEXT.OLC_PERU;
        LNRRATL.INT_CHRG_MTH = LNCSEXT.CPND_RTY;
        LNRRATL.RATE_TYPE = LNCSEXT.CPNDRATT;
        LNRRATL.RATE_CLAS = LNCSEXT.CPNDRATC;
        LNRRATL.DIF_IRAT_PER = LNCSEXT.CPND_PCT;
        LNRRATL.DIF_IRAT_PNT = LNCSEXT.CPND_SPR;
        LNRRATL.VARIATION_METHOD = LNCSEXT.CPND_TYP;
        LNCRRATL.FUNC = 'U';
        CEP.TRC(SCCGWA, LNCSEXT.CTA_NO);
        CEP.TRC(SCCGWA, LNCSEXT.VAL_DATE);
        S000_UPD_LNTRATL();
        if (pgmRtn) return;
    }
    public void B010_UPD_P_LNTRATL_PROC() throws IOException,SQLException,Exception {
        LNRRATL.INT_CHRG_MTH = LNCSEXT.ABUS_RAT;
        LNRRATL.EFF_RAT = LNCSEXT.ABUSIRAT;
        LNRRATL.RATE_TYPE = "" + LNCSEXT.ABUSRATT;
        JIBS_tmp_int = LNRRATL.RATE_TYPE.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) LNRRATL.RATE_TYPE = "0" + LNRRATL.RATE_TYPE;
        LNRRATL.RATE_CLAS = LNCSEXT.ABUSRATC;
        LNRRATL.RATE_CLAS = " ";
        LNRRATL.DIF_IRAT_PER = LNCSEXT.ABUSPCT;
        LNRRATL.DIF_IRAT_PNT = LNCSEXT.ABUSSPR;
        LNRRATL.VARIATION_METHOD = LNCSEXT.ABUS_TYP.charAt(0);
        LNCRRATL.FUNC = 'U';
        CEP.TRC(SCCGWA, LNCSEXT.CTA_NO);
        CEP.TRC(SCCGWA, LNCSEXT.VAL_DATE);
        S000_UPD_LNTRATL();
        if (pgmRtn) return;
    }
    public void B010_ADD_LNTRATH_PROC() throws IOException,SQLException,Exception {
        B010_READ_LNTRATH_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            if (WS_INT_TYP == 'N') {
                IBS.init(SCCGWA, LNCRATHM);
                LNCRATHM.FUNC = '0';
                LNRRATH.KEY.CONTRACT_NO = LNCSEXT.CTA_NO;
                LNRRATH.KEY.SUB_CTA_NO = 0;
                LNRRATH.KEY.RATE_TYP = WS_INT_TYP;
                LNRRATH.KEY.RAT_CHG_DT = LNCSEXT.VAL_DATE;
                LNRRATH.BASE_RATE = LNCSEXT.RAT_REF;
                LNRRATH.BASE_RATE_FLG = 'Y';
                LNRRATH.RATE_KIND = LNCSEXT.IRAT_TYP.charAt(0);
                LNRRATH.INT_RAT = LNCSEXT.ALL_IN_R;
                LNRRATH.RATE_FROM_SEQ = 0;
                S000_CALL_LNZRATHM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "NORMAL PROC");
            } else if (WS_INT_TYP == 'O') {
                IBS.init(SCCGWA, LNCRATHM);
                LNCRATHM.FUNC = '0';
                LNRRATH.KEY.CONTRACT_NO = LNCSEXT.CTA_NO;
                LNRRATH.KEY.SUB_CTA_NO = 0;
                LNRRATH.KEY.RATE_TYP = WS_INT_TYP;
                LNRRATH.KEY.RAT_CHG_DT = LNCSEXT.VAL_DATE;
                LNRRATH.BASE_RATE = 0;
                LNRRATH.BASE_RATE_FLG = 'Y';
                LNRRATH.RATE_KIND = LNCSEXT.PEN_RATT.charAt(0);
                LNRRATH.INT_RAT = LNCSEXT.PEN_IRAT;
                LNRRATH.RATE_FROM_SEQ = 0;
                S000_CALL_LNZRATHM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "B010-ADD-LNTRATH-PROC O");
                CEP.TRC(SCCGWA, LNRRATH.KEY.RATE_TYP);
                CEP.TRC(SCCGWA, LNRRATH.INT_RAT);
            } else if (WS_INT_TYP == 'L') {
                IBS.init(SCCGWA, LNCRATHM);
                LNCRATHM.FUNC = '0';
                LNRRATH.KEY.CONTRACT_NO = LNCSEXT.CTA_NO;
                LNRRATH.KEY.SUB_CTA_NO = 0;
                LNRRATH.KEY.RATE_TYP = WS_INT_TYP;
                LNRRATH.KEY.RAT_CHG_DT = LNCSEXT.VAL_DATE;
                LNRRATH.BASE_RATE = 0;
                LNRRATH.BASE_RATE_FLG = 'Y';
                LNRRATH.RATE_KIND = LNCSEXT.CPNDRATT.charAt(0);
                LNRRATH.INT_RAT = LNCSEXT.OLC_PERU;
                LNRRATH.RATE_FROM_SEQ = 0;
                S000_CALL_LNZRATHM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "B010-ADD-LNTRATH-PROC L");
                CEP.TRC(SCCGWA, LNRRATH.KEY.RATE_TYP);
                CEP.TRC(SCCGWA, LNRRATH.INT_RAT);
            } else if (WS_INT_TYP == 'P') {
                IBS.init(SCCGWA, LNCRATHM);
                LNCRATHM.FUNC = '0';
                LNRRATH.KEY.CONTRACT_NO = LNCSEXT.CTA_NO;
                LNRRATH.KEY.SUB_CTA_NO = 0;
                LNRRATH.KEY.RATE_TYP = WS_INT_TYP;
                LNRRATH.KEY.RAT_CHG_DT = LNCSEXT.VAL_DATE;
                LNRRATH.BASE_RATE = 0;
                LNRRATH.BASE_RATE_FLG = 'Y';
                LNRRATH.RATE_KIND = LNCSEXT.ABUSRATT;
                LNRRATH.INT_RAT = LNCSEXT.ABUSIRAT;
                LNRRATH.RATE_FROM_SEQ = 0;
                S000_CALL_LNZRATHM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "ABUS PROC");
            } else {
            }
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRATHM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(LNCMSG_ERROR_MSG.LN_ERR_RATH_EXIST)) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATH_EXIST, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_READ_LNTRATH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRATHM);
        LNCRATHM.FUNC = '3';
        LNRRATH.KEY.CONTRACT_NO = LNCSEXT.CTA_NO;
        LNRRATH.KEY.SUB_CTA_NO = 0;
        LNRRATH.KEY.RATE_TYP = WS_INT_TYP;
        LNRRATH.KEY.RAT_CHG_DT = LNCSEXT.VAL_DATE;
        CEP.TRC(SCCGWA, LNRRATH.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRATH.KEY.RATE_TYP);
        CEP.TRC(SCCGWA, LNRRATH.KEY.RAT_CHG_DT);
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        IBS.READ(SCCGWA, LNRRATH, LNTRATH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "I CANNOT FIND");
        }
    }
    public void B010_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        if (LNCSEXT.VAL_DATE == 0 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            B012_GET_VAL_DATE();
            if (pgmRtn) return;
        }
        if (LNCSEXT.VAL_DATE < LNRCONT.LAST_F_VAL_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_EXTN_VAL_DATE, LNCSEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "YE111");
        CEP.TRC(SCCGWA, LNCSEXT.VAL_DATE);
        if (LNCSEXT.VAL_DATE > SCCGWA.COMM_AREA.AC_DATE 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            B051_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            if (WS_EXIST_FWDH_FLAG == 'Y') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_REPEAT_REC, LNCSEXT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            B021_EXTN_PROC();
            if (pgmRtn) return;
            B022_WRITE_EXTN();
            if (pgmRtn) return;
            if (LNCSEXT.FUNC == 'A') {
                R000_WTRITE_HIS_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNCSEXT.VAL_DATE == SCCGWA.COMM_AREA.AC_DATE 
            || SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            if (LNCSEXT.EXT_AMT != 0 
                && WS_B_AMT > LNCSEXT.EXT_AMT) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_B_AMT, LNCSEXT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                B051_QUERY_RECORD_PROC();
                if (pgmRtn) return;
            }
            if (WS_EXIST_FWDH_FLAG == 'Y' 
                && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PEPEAT_ONE_DAY, LNCSEXT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            B020_CHECK_VAL_EXTN();
            if (pgmRtn) return;
            B021_EXTN_PROC();
            if (pgmRtn) return;
            B022_WRITE_EXTN();
            if (pgmRtn) return;
            B010_UEXT_PROC();
            if (pgmRtn) return;
        }
        if (LNCSEXT.VAL_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_EXT_VAL_DATE, LNCSEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_ADD_BALH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCBALHM);
        LNCBALHM.FUNC = '4';
        LNCBALHM.REC_DATA.KEY.CONTRACT_NO = LNCSEXT.CTA_NO;
        LNCBALHM.REC_DATA.KEY.SUB_CTA_NO = 0;
        LNCBALHM.REC_DATA.KEY.CTNR_NO = 2;
        LNCBALHM.REC_DATA.KEY.TXN_DT = LNCSEXT.VAL_DATE;
        CEP.TRC(SCCGWA, LNCSEXT.CTA_NO);
        CEP.TRC(SCCGWA, LNCBALHM.REC_DATA.KEY.CTNR_NO);
        CEP.TRC(SCCGWA, LNCBALHM.REC_DATA.ID_FLG);
        CEP.TRC(SCCGWA, LNCBALHM.REC_DATA.BAL);
        CEP.TRC(SCCGWA, LNCBALHM.REC_DATA.KEY.TXN_DT);
        S000_CALL_LNZBALHM();
        if (pgmRtn) return;
        if (LNCBALHM.RC.RC_RTNCODE == 0) {
            CEP.TRC(SCCGWA, "READ LNTBALH");
            T00_REWRITE_LNTBALH();
            if (pgmRtn) return;
        }
    }
    public void T00_REWRITE_LNTBALH() throws IOException,SQLException,Exception {
        LNCBALHM.FUNC = '2';
        LNCBALHM.REC_DATA.ID_FLG = 'A';
        LNCBALHM.REC_DATA.BAL += LNCBALHM.REC_DATA.AMT;
        LNCBALHM.REC_DATA.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCBALHM.REC_DATA.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_LNZBALHM();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZBALHM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALH-MAINT", LNCBALHM);
        if (LNCBALHM.RC.RC_RTNCODE != 0) {
            LNCSEXT.RC.RC_APP = LNCBALHM.RC.RC_APP;
            LNCSEXT.RC.RC_RTNCODE = LNCBALHM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_VAL_EXTN() throws IOException,SQLException,Exception {
        S000_INQUIRE_ICTL();
        if (pgmRtn) return;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(33 - 1, 33 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(35 - 1, 35 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, LNREXTN);
            LNREXTN.KEY.CONTRACT_NO = LNCUEXT.CTA_NO;
            LNREXTN.KEY.VAL_DT = LNCUEXT.VAL_DATE;
            LNREXTN.KEY.STATUS = '2';
            T00_READ_LNTEXTN_1();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_EXTN_FLG_E, LNCUEXT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B012_GET_VAL_DATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSEXT.EE_DATE);
        CEP.TRC(SCCGWA, WS_MAT_DATE);
        if (LNCSEXT.EE_DATE > WS_MAT_DATE) {
            if (WS_MAT_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                LNCSEXT.VAL_DATE = WS_MAT_DATE;
            } else {
                LNCSEXT.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            }
            if (LNCSEXT.VAL_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                LNCSEXT.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            }
        } else {
            if (LNCSEXT.EE_DATE < WS_MAT_DATE) {
                LNCSEXT.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            }
        }
        WS_VAL_DATE2 = LNCSEXT.VAL_DATE;
        CEP.TRC(SCCGWA, LNCSEXT.VAL_DATE);
    }
    public void B010_UEXT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCUEXT);
        LNCUEXT.CTA_TYP = LNCSEXT.CTA_TYP;
        LNCUEXT.CTA_NO = LNCSEXT.CTA_NO;
        LNCUEXT.BR = LNCSEXT.BR;
        LNCUEXT.CI_NO = LNCSEXT.CI_NO;
        LNCUEXT.PROD_CD = LNCSEXT.PROD_CD;
        LNCUEXT.CCY = LNCSEXT.CCY;
        LNCUEXT.P_AMT = LNCSEXT.P_AMT;
        LNCUEXT.B_AMT = LNCSEXT.B_AMT;
        LNCUEXT.OE_DATE = LNCSEXT.OE_DATE;
        CEP.TRC(SCCGWA, LNCUEXT.OE_DATE);
        LNCUEXT.EE_DATE = LNCSEXT.EE_DATE;
        if (LNCSEXT.OE_DATE > LNCSEXT.EE_DATE) {
            LNCUEXT.EXT_FLG = '2';
        } else {
            LNCUEXT.EXT_FLG = '1';
        }
        LNCUEXT.VAL_DATE = LNCSEXT.VAL_DATE;
        LNCUEXT.EXT_AMT = LNCSEXT.EXT_AMT;
        LNCUEXT.REASON = LNCSEXT.REASON;
        LNCUEXT.RAT_INFO.FLT_MTH = LNCSEXT.FLOAT_MTH;
        CEP.TRC(SCCGWA, LNCUEXT.RAT_INFO.FLT_MTH);
        LNCUEXT.RAT_INFO.RAT_FLG = LNCSEXT.RAT_FLG;
        LNCUEXT.RAT_INFO.IRAT_CD = LNCSEXT.IRAT_TYP;
        LNCUEXT.RAT_INFO.IRATCLS = LNCSEXT.RAT_PERD;
        LNCUEXT.RAT_INFO.INT_RTV = LNCSEXT.RAT_VARI;
        LNCUEXT.RAT_INFO.RAT_PCT = LNCSEXT.RAT_PECT;
        LNCUEXT.RAT_INFO.INT_RAT = LNCSEXT.RAT_REF;
        CEP.TRC(SCCGWA, LNCSEXT.RAT_REF);
        CEP.TRC(SCCGWA, LNCUEXT.RAT_INFO.INT_RAT);
        LNCUEXT.RAT_INFO.ALL_RAT = LNCSEXT.ALL_IN_R;
        CEP.TRC(SCCGWA, LNCSEXT.ALL_IN_R);
        CEP.TRC(SCCGWA, LNCUEXT.RAT_INFO.ALL_RAT);
        LNCUEXT.RAT_INFO.PEN_RRAT = LNCSEXT.PEN_RRAT;
        LNCUEXT.RAT_INFO.PEN_TYP = LNCSEXT.PEN_TYP;
        LNCUEXT.RAT_INFO.PEN_RATT = LNCSEXT.PEN_RATT;
        LNCUEXT.RAT_INFO.PEN_RATC = LNCSEXT.PEN_RATC;
        LNCUEXT.RAT_INFO.PEN_SPR = LNCSEXT.PEN_SPR;
        LNCUEXT.RAT_INFO.PEN_PCT = LNCSEXT.PEN_PCT;
        LNCUEXT.RAT_INFO.PEN_IRAT = LNCSEXT.PEN_IRAT;
        CEP.TRC(SCCGWA, LNCSEXT.PEN_IRAT);
        CEP.TRC(SCCGWA, LNCUEXT.RAT_INFO.PEN_IRAT);
        LNCUEXT.RAT_INFO.CPND_USE = LNCSEXT.CPND_USE;
        LNCUEXT.RAT_INFO.INT_MTH = LNCSEXT.INT_MTH;
        LNCUEXT.RAT_INFO.CPND_RRAT = LNCSEXT.CPND_RTY;
        LNCUEXT.RAT_INFO.CPND_TYP = LNCSEXT.CPND_TYP;
        LNCUEXT.RAT_INFO.CPNDRATT = LNCSEXT.CPNDRATT;
        LNCUEXT.RAT_INFO.CPNDRATC = LNCSEXT.CPNDRATC;
        LNCUEXT.RAT_INFO.CPND_SPR = LNCSEXT.CPND_SPR;
        LNCUEXT.RAT_INFO.CPND_PCT = LNCSEXT.CPND_PCT;
        LNCUEXT.RAT_INFO.CPND_IRAT = LNCSEXT.OLC_PERU;
        LNCUEXT.RAT_INFO.ABUS_RAT = LNCSEXT.ABUS_RAT;
        LNCUEXT.RAT_INFO.ABUS_TYP = LNCSEXT.ABUSRATT;
        LNCUEXT.RAT_INFO.ABUSRATT = LNCSEXT.ABUS_TYP;
        LNCUEXT.RAT_INFO.ABUSRATC = LNCSEXT.ABUSRATC;
        LNCUEXT.RAT_INFO.ABUSSPR = LNCSEXT.ABUSSPR;
        LNCUEXT.RAT_INFO.ABUSPCT = LNCSEXT.ABUSPCT;
        LNCUEXT.RAT_INFO.ABUSIRAT = LNCSEXT.ABUSIRAT;
        S000_CALL_LNZUEXT();
        if (pgmRtn) return;
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNREXTN);
        LNREXTN.KEY.CONTRACT_NO = LNCSEXT.CTA_NO;
        B051_QUERY_RECORD_PROC();
        if (pgmRtn) return;
        if (WS_EXIST_FWDH_FLAG == 'N' 
            || (WS_EXIST_FWDH_FLAG == 'Y' 
            && WS_EXT_FLG == '3')) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FWDH_NOTFND, LNCSEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_EXIST_FWDH_FLAG == 'Y' 
            && WS_EXT_FLG != '3') {
            LNREXTN.KEY.CONTRACT_NO = LNCSEXT.CTA_NO;
            LNREXTN.KEY.EXT_FLG = WS_EXT_FLG;
            LNREXTN.KEY.VAL_DT = WS_VAL_DATE;
            LNREXTN.KEY.STATUS = '4';
            B031_DELETE_OUTPUT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNREXTN.KEY.CONTRACT_NO);
            CEP.TRC(SCCGWA, LNREXTN.KEY.VAL_DT);
            CEP.TRC(SCCGWA, LNREXTN.KEY.STATUS);
            CEP.TRC(SCCGWA, LNREXTN.KEY.EXT_FLG);
            T00_REUPDATE_LNTEXTN();
            if (pgmRtn) return;
            if (WS_EXIST_FWDH_FLAG == 'Y') {
                T00_DELETE_LNTEXTN();
                if (pgmRtn) return;
            }
            LNREXTN.KEY.STATUS = '1';
            CEP.TRC(SCCGWA, LNREXTN.KEY.CONTRACT_NO);
            CEP.TRC(SCCGWA, LNREXTN.KEY.VAL_DT);
            CEP.TRC(SCCGWA, LNREXTN.KEY.STATUS);
            CEP.TRC(SCCGWA, LNREXTN.KEY.EXT_FLG);
            T00_REUPDATE_LNTEXTN();
            if (pgmRtn) return;
            T00_DELETE_LNTEXTN();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNREXTN.KEY.CONTRACT_NO);
            CEP.TRC(SCCGWA, LNREXTN.KEY.VAL_DT);
            CEP.TRC(SCCGWA, LNREXTN.KEY.STATUS);
            CEP.TRC(SCCGWA, LNREXTN.KEY.EXT_FLG);
            LNREXTN.KEY.STATUS = '4';
            CEP.TRC(SCCGWA, LNREXTN.KEY.CONTRACT_NO);
            CEP.TRC(SCCGWA, LNREXTN.KEY.VAL_DT);
            CEP.TRC(SCCGWA, LNREXTN.KEY.STATUS);
            CEP.TRC(SCCGWA, LNREXTN.KEY.EXT_FLG);
            T00_WRITE_LNTEXTN();
            if (pgmRtn) return;
        }
        R000_WTRITE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B031_DELETE_OUTPUT() throws IOException,SQLException,Exception {
        LNCSEXT.CTA_NO = LNREXTN.KEY.CONTRACT_NO;
        LNCSEXT.VAL_DATE = LNREXTN.KEY.VAL_DT;
        LNCSEXT.B_AMT = LNREXTN.OLD_BAL;
        LNCSEXT.OE_DATE = LNREXTN.OLD_DUE_DT;
        LNCSEXT.EE_DATE = LNREXTN.NEW_DUE_DT;
        LNCSEXT.REASON = LNREXTN.EXT_RSN;
        LNCSEXT.EXT_AMT = LNREXTN.EXT_AMT;
    }
    public void B020_MODIFY_RECORD_PROC() throws IOException,SQLException,Exception {
        if (LNCSEXT.VAL_DATE == 0 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            B012_GET_VAL_DATE();
            if (pgmRtn) return;
        }
        if (LNCSEXT.VAL_DATE < LNRCONT.LAST_F_VAL_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_EXTN_VAL_DATE, LNCSEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCSEXT.VAL_DATE);
        CEP.TRC(SCCGWA, WS_VAL_DATE);
        B051_QUERY_RECORD_PROC();
        if (pgmRtn) return;
        if (WS_EXIST_FWDH_FLAG == 'N' 
            || (WS_EXIST_FWDH_FLAG == 'Y' 
            && WS_EXT_FLG == '3')) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FWDH_NOTFND, LNCSEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNREXTN.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNREXTN.KEY.STATUS);
        CEP.TRC(SCCGWA, LNREXTN.KEY.EXT_FLG);
        CEP.TRC(SCCGWA, LNREXTN.KEY.VAL_DT);
        IBS.init(SCCGWA, LNREXTN);
        IBS.init(SCCGWA, LNCREXTN);
        LNREXTN.KEY.CONTRACT_NO = LNCSEXT.CTA_NO;
        LNREXTN.KEY.EXT_FLG = WS_EXT_FLG;
        LNREXTN.KEY.VAL_DT = WS_VAL_DATE;
        LNREXTN.KEY.STATUS = WS_STATUS;
        T00_REUPDATE_LNTEXTN();
        if (pgmRtn) return;
        T00_DELETE_LNTEXTN();
        if (pgmRtn) return;
        B010_ADD_RECORD_PROC();
        if (pgmRtn) return;
        R000_WTRITE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        if (LNCSEXT.VAL_DATE == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_EXTN_VAL_DT_INPUT, LNCSEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSEXT.CTA_NO.trim().length() == 0 
            || LNCSEXT.CTA_TYP == ' ') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INPUT, LNCSEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNREXTN);
        LNREXTN.KEY.CONTRACT_NO = LNCSEXT.CTA_NO;
        LNREXTN.KEY.STATUS = '2';
        LNREXTN.KEY.EXT_FLG = LNCSEXT.EXT_FLG;
        LNREXTN.KEY.VAL_DT = LNCSEXT.VAL_DATE;
        CEP.TRC(SCCGWA, LNREXTN.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNREXTN.KEY.STATUS);
        CEP.TRC(SCCGWA, LNREXTN.KEY.EXT_FLG);
        CEP.TRC(SCCGWA, LNREXTN.KEY.VAL_DT);
        T00_READ_LNTEXTN_2();
        if (pgmRtn) return;
        LNCSEXT.CTA_NO = LNREXTN.KEY.CONTRACT_NO;
        LNCSEXT.VAL_DATE = LNREXTN.KEY.VAL_DT;
        LNCSEXT.OE_DATE = LNREXTN.OLD_DUE_DT;
        LNCSEXT.EE_DATE = LNREXTN.NEW_DUE_DT;
        CEP.TRC(SCCGWA, LNREXTN.OLD_DUE_DT);
        CEP.TRC(SCCGWA, LNREXTN.NEW_DUE_DT);
        CEP.TRC(SCCGWA, LNCSEXT.OE_DATE);
        CEP.TRC(SCCGWA, LNCSEXT.EE_DATE);
        LNCSEXT.REASON = LNREXTN.EXT_RSN;
        LNCSEXT.EXT_AMT = LNREXTN.EXT_AMT;
        LNCSEXT.TR_DATE = LNREXTN.CRT_DATE;
        LNCSEXT.IRAT_TYP = LNREXTN.INT_RATE_TYPE1;
        LNCSEXT.ALL_IN_R = LNREXTN.ALL_IN_RATE;
        B060_GET_INFO_PROC();
        if (pgmRtn) return;
    }
    public void B051_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNREXTN);
        LNREXTN.KEY.CONTRACT_NO = LNCSEXT.CTA_NO;
        LNREXTN.KEY.STATUS = K_EXTN_STATUS;
        T00_READ_LNTEXTN();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_EXIST_FWDH_FLAG = 'Y';
            CEP.TRC(SCCGWA, LNREXTN.KEY.CONTRACT_NO);
            CEP.TRC(SCCGWA, LNREXTN.KEY.VAL_DT);
            CEP.TRC(SCCGWA, LNREXTN.KEY.STATUS);
            CEP.TRC(SCCGWA, LNREXTN.KEY.EXT_FLG);
            WS_STATUS = LNREXTN.KEY.STATUS;
            WS_VAL_DATE = LNREXTN.KEY.VAL_DT;
            WS_EXT_FLG = LNREXTN.KEY.EXT_FLG;
        } else {
            WS_EXIST_FWDH_FLAG = 'N';
        }
    }
    public void B021_EXTN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNREXTN);
        IBS.init(SCCGWA, LNCREXTN);
        LNREXTN.KEY.CONTRACT_NO = LNCSEXT.CTA_NO;
        LNREXTN.KEY.VAL_DT = LNCSEXT.VAL_DATE;
        LNREXTN.OLD_BAL = LNCSEXT.B_AMT;
        LNREXTN.OLD_DUE_DT = WS_MAT_DATE;
        LNREXTN.NEW_DUE_DT = LNCSEXT.EE_DATE;
        CEP.TRC(SCCGWA, LNCSEXT.EE_DATE);
        CEP.TRC(SCCGWA, WS_MAT_DATE);
        if (LNCSEXT.EE_DATE > WS_MAT_DATE) {
            LNREXTN.KEY.EXT_FLG = K_EXTN_EXT_FLG_1;
            LNCUEXT.EXT_FLG = K_EXTN_EXT_FLG_1;
        }
        if (LNCSEXT.EE_DATE < WS_MAT_DATE) {
            LNREXTN.KEY.EXT_FLG = K_EXTN_EXT_FLG_2;
            LNCUEXT.EXT_FLG = K_EXTN_EXT_FLG_2;
        }
        CEP.TRC(SCCGWA, LNREXTN.KEY.STATUS);
        LNREXTN.EXT_RSN = LNCSEXT.REASON;
        LNREXTN.EXT_AMT = LNCSEXT.EXT_AMT;
        CEP.TRC(SCCGWA, LNREXTN.EXT_AMT);
        LNREXTN.RATE_FLG = LNCSEXT.RAT_FLG;
        LNREXTN.NEXT_FLT_DT = LNCSEXT.NEXT_FLT_DT;
        LNREXTN.FLT_PERD_UNIT = LNCSEXT.FLT_PERD_UN;
        LNREXTN.FLT_PERD = LNCSEXT.FLT_PERD;
        LNREXTN.FLOAT_FLG = LNCSEXT.FLT_FLG;
        LNREXTN.FLOAT_DAY = LNCSEXT.FLT_DAY;
        LNREXTN.FLT_FIX_DAYS = LNCSEXT.FLTFIX_D;
        LNREXTN.FLOAT_MTH = LNCSEXT.FLOAT_MTH;
        LNREXTN.CMP_METHOD = LNCSEXT.CMP_METHOD;
        LNREXTN.INT_RATE_TYPE1 = LNCSEXT.IRAT_TYP;
        LNREXTN.INT_RATE_CLAS1 = LNCSEXT.RAT_PERD;
        LNREXTN.RATE_VARIATION1 = LNCSEXT.RAT_VARI;
        LNREXTN.RATE_PECT1 = LNCSEXT.RAT_PECT;
        LNREXTN.REF_RAT1 = LNCSEXT.RAT_REF;
        LNREXTN.ALL_IN_RATE = LNCSEXT.ALL_IN_R;
        LNREXTN.PEN_RRAT = LNCSEXT.PEN_RRAT;
        LNREXTN.PEN_TYP = LNCSEXT.PEN_TYP;
        LNREXTN.PEN_RATT = LNCSEXT.PEN_RATT;
        LNREXTN.PEN_RATC = LNCSEXT.PEN_RATC;
        LNREXTN.PEN_SPR = LNCSEXT.PEN_SPR;
        LNREXTN.PEN_PCT = LNCSEXT.PEN_PCT;
        LNREXTN.PEN_IRAT = LNCSEXT.PEN_IRAT;
        LNREXTN.CPND_USE = LNCSEXT.CPND_USE;
        LNREXTN.INT_MTH = LNCSEXT.INT_MTH;
        LNREXTN.CPND_RTY = LNCSEXT.CPND_RTY;
        LNREXTN.CPND_TYP = LNCSEXT.CPND_TYP;
        LNREXTN.CPNDRATT = LNCSEXT.CPNDRATT;
        LNREXTN.CPNDRATC = LNCSEXT.CPNDRATC;
        LNREXTN.CPND_SPR = LNCSEXT.CPND_SPR;
        LNREXTN.CPND_PCT = LNCSEXT.CPND_PCT;
        LNREXTN.OLC_PERU = LNCSEXT.OLC_PERU;
        LNREXTN.ABUS_RAT = LNCSEXT.ABUS_RAT;
        LNREXTN.ABUS_TYP = LNCSEXT.ABUS_TYP.charAt(0);
        LNREXTN.ABUSRATT = "" + LNCSEXT.ABUSRATT;
        JIBS_tmp_int = LNREXTN.ABUSRATT.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) LNREXTN.ABUSRATT = "0" + LNREXTN.ABUSRATT;
        LNREXTN.ABUSRATC = LNCSEXT.ABUSRATC;
        LNREXTN.ABUSSPR = LNCSEXT.ABUSSPR;
        LNREXTN.ABUSPCT = LNCSEXT.ABUSPCT;
        LNREXTN.ABUSIRAT = LNCSEXT.ABUSIRAT;
        LNREXTN.SUP1 = SCCGWA.COMM_AREA.SUP1_ID;
        LNREXTN.SUP2 = SCCGWA.COMM_AREA.SUP2_ID;
        if (LNCSEXT.FUNC == 'D') {
            LNREXTN.KEY.STATUS = K_DEL_STATUS;
        } else {
            if (LNCSEXT.VAL_DATE == SCCGWA.COMM_AREA.AC_DATE 
                || SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                LNREXTN.KEY.STATUS = '2';
            } else {
                LNREXTN.KEY.STATUS = '1';
            }
        }
        CEP.TRC(SCCGWA, LNREXTN.KEY.STATUS);
    }
    public void R000_WTRITE_HIS_PROC() throws IOException,SQLException,Exception {
        R000_READY_WRITE_HIS_PROC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.AC = LNCSEXT.CTA_NO;
        BPCPNHIS.INFO.CI_NO = LNCSEXT.CI_NO;
        BPCPNHIS.INFO.REF_NO = LNCSEXT.CI_NO;
        CEP.TRC(SCCGWA, LNCSEXT.FUNC);
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS;
        if (LNCSEXT.FUNC == 'A') {
            BPCPNHIS.INFO.TX_CD = "0134070";
            BPCPNHIS.INFO.TX_TYP = 'A';
        } else {
            if (LNCSEXT.FUNC == 'M') {
                BPCPNHIS.INFO.TX_CD = "0134072";
                BPCPNHIS.INFO.TX_TYP = 'M';
            } else {
                BPCPNHIS.INFO.TX_CD = "0134073";
                BPCPNHIS.INFO.TX_TYP = 'D';
            }
        }
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP);
        BPCPNHIS.INFO.FMT_ID = "LNCHUEXT";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 806;
        BPCPNHIS.INFO.OLD_DAT_PT = LNCHUEXO;
        BPCPNHIS.INFO.NEW_DAT_PT = LNCHUEXN;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP);
    }
    public void R000_READY_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCHUEXN);
        LNCHUEXN.FUNC = WS_FUNC_CODE;
        LNCHUEXO.FUNC = WS_FUNC_CODE;
        LNCHUEXN.CTA_TYP = LNCSEXT.CTA_TYP;
        LNCHUEXO.CTA_TYP = LNCSEXT.CTA_TYP;
        LNCHUEXN.CTA_NO = LNCSEXT.CTA_NO;
        LNCHUEXO.CTA_NO = LNCSEXT.CTA_NO;
        LNCHUEXN.CI_NO = LNCSEXT.CI_NO;
        LNCHUEXO.CI_NO = LNCSEXT.CI_NO;
        LNCHUEXN.CI_CNM = LNCSEXT.CI_CNM;
        LNCHUEXO.CI_CNM = LNCSEXT.CI_CNM;
        LNCHUEXN.BR = LNCSEXT.BR;
        LNCHUEXO.BR = LNCSEXT.BR;
        LNCHUEXN.PROD_CD = LNCSEXT.PROD_CD;
        LNCHUEXO.PROD_CD = LNCSEXT.PROD_CD;
        LNCHUEXN.PROD_NM = LNCSEXT.PROD_NM;
        LNCHUEXO.PROD_NM = LNCSEXT.PROD_NM;
        LNCHUEXN.CCY = LNCSEXT.CCY;
        LNCHUEXO.CCY = LNCSEXT.CCY;
        LNCHUEXN.P_AMT = LNCSEXT.P_AMT;
        LNCHUEXO.P_AMT = LNCSEXT.P_AMT;
        LNCHUEXN.B_AMT = LNCSEXT.B_AMT;
        LNCHUEXO.B_AMT = LNCSEXT.B_AMT;
        LNCHUEXN.OE_DATE = LNCSEXT.OE_DATE;
        LNCHUEXO.OE_DATE = LNCSEXT.OE_DATE;
        LNCHUEXN.EE_DATE = LNCSEXT.EE_DATE;
        LNCHUEXO.EE_DATE = LNCSEXT.EE_DATE;
        LNCHUEXN.VAL_DATE = LNCSEXT.VAL_DATE;
        LNCHUEXO.VAL_DATE = LNCSEXT.VAL_DATE;
        LNCHUEXN.EXT_AMT = LNCSEXT.EXT_AMT;
        LNCHUEXO.EXT_AMT = LNCSEXT.EXT_AMT;
        LNCHUEXN.REASON = LNCSEXT.REASON;
        LNCHUEXO.REASON = LNCSEXT.REASON;
        if (LNCSEXT.VAL_DATE == SCCGWA.COMM_AREA.AC_DATE) {
            LNCHUEXN.STS = '1';
            LNCHUEXO.STS = '1';
        } else {
            if (WS_FUNC_CODE != 'D') {
                LNCHUEXN.STS = '0';
                LNCHUEXO.STS = '0';
            }
        }
    }
    public void B022_WRITE_EXTN() throws IOException,SQLException,Exception {
        LNCREXTN.FUNC = 'A';
        S000_CALL_LNZREXTN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void B060_GET_INFO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCLNQ);
        LNCCLNQ.DATA.CONT_NO = LNCSEXT.CTA_NO;
        LNCCLNQ.FUNC = '3';
        S000_CALL_LNZICLNQ();
        if (pgmRtn) return;
        LNCSEXT.B_AMT = LNCCLNQ.DATA.TOT_BAL;
        LNCSEXT.CI_NO = LNCCLNQ.DATA.CI_NO;
        LNCSEXT.CI_CNM = LNCCLNQ.DATA.CI_CNAME;
        LNCSEXT.CCY = LNCCLNQ.DATA.CCY;
        LNCSEXT.P_AMT = LNCCLNQ.DATA.PRIN;
        LNCSEXT.PROD_CD = LNCCLNQ.DATA.PROD_CD;
        LNCSEXT.BR = LNCCLNQ.DATA.DOMI_BR;
        CEP.TRC(SCCGWA, LNCCLNQ.DATA.TOT_BAL);
        LNCSEXT.B_AMT = LNCCLNQ.DATA.TOT_BAL;
        CEP.TRC(SCCGWA, LNCSEXT.B_AMT);
    }
    public void B080_CHECK_ICTL_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSSTBL);
        LNCSSTBL.CON_NO = LNCSEXT.CTA_NO;
        LNCSSTBL.TR_CODE = "" + SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        JIBS_tmp_int = LNCSSTBL.TR_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) LNCSSTBL.TR_CODE = "0" + LNCSSTBL.TR_CODE;
        CEP.TRC(SCCGWA, LNCSSTBL.TR_CODE);
        S000_CALL_LNZSSTBL();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZSSTBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-CHECK-CI-STS", LNCSSTBL);
        if (LNCSSTBL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSSTBL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSEXT.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSEXT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZUEXT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-CONTRACT-EXT", LNCUEXT);
        CEP.TRC(SCCGWA, LNCUEXT.RC);
        CEP.TRC(SCCGWA, LNCUEXT.RC.RC_RTNCODE);
        if (LNCUEXT.RC.RC_RTNCODE != 0) {
            LNCSEXT.RC.RC_APP = IBS.CLS2CPY(SCCGWA, LNCUEXT.RC);
            LNCSEXT.RC.RC_RTNCODE = LNCUEXT.RC.RC_RTNCODE;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSEXT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_INQUIRE_CONT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCONT);
        IBS.init(SCCGWA, LNRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = WS_CTA_NO;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
    }
    public void S000_INQUIRE_ICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRICTL);
        IBS.init(SCCGWA, LNRICTL);
        LNCRICTL.FUNC = 'I';
        LNRICTL.KEY.CONTRACT_NO = WS_CTA_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RETURN_INFO != 'F') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSEXT.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSEXT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRICTL() throws IOException,SQLException,Exception {
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 425;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        if (LNCRICTL.RC.RC_CODE != 0) {
            if (LNCRICTL.RETURN_INFO == 'E' 
                || LNCRICTL.RETURN_INFO == 'N') {
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRICTL.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSEXT.RC);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSEXT.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSEXT.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSEXT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZREXTN() throws IOException,SQLException,Exception {
        LNCREXTN.REC_PTR = LNREXTN;
        LNCREXTN.REC_LEN = 595;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTEXTN", LNCREXTN);
        if (LNCREXTN.RETURN_INFO == 'N' 
            || LNCREXTN.RETURN_INFO == 'E') {
            WS_EXIST_FWDH_FLAG = 'N';
        } else {
            if (LNCREXTN.RETURN_INFO == 'D') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY_SAMEDAY, LNCSEXT.RC);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSEXT.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                if (LNCREXTN.RC.RC_CODE != 0) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCREXTN.RC);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSEXT.RC);
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSEXT.RC);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (LNCREXTN.RC.RC_CODE == 0 
            && (LNCSEXT.FUNC == 'A' 
            || LNCSEXT.FUNC == 'M')) {
            WS_EXIST_FWDH_FLAG = 'Y';
        }
    }
    public void S000_CALL_LNZICLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-LN-INFO", LNCCLNQ);
        if (LNCCLNQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCLNQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSEXT.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSEXT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CHK-PROD", LNCSCKPD);
    }
    public void S000_CALL_LNZRRATL() throws IOException,SQLException,Exception {
        LNTRATL_RD = new DBParm();
        LNTRATL_RD.TableName = "LNTRATL";
        LNTRATL_RD.where = "CONTRACT_NO = :LNRRATL.KEY.CONTRACT_NO "
            + "AND OVD_KND = :LNRRATL.KEY.OVD_KND "
            + "AND ACTV_DT <= :LNRRATL.KEY.ACTV_DT";
        LNTRATL_RD.fst = true;
        LNTRATL_RD.order = "ACTV_DT DESC";
        IBS.READ(SCCGWA, LNRRATL, this, LNTRATL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATL_NOTFND, WS_ERR_MSG);
        }
    }
    public void S000_READUPD_LNTRATL() throws IOException,SQLException,Exception {
        LNTRATL_RD = new DBParm();
        LNTRATL_RD.TableName = "LNTRATL";
        LNTRATL_RD.upd = true;
        LNTRATL_RD.where = "CONTRACT_NO = :LNRRATL.KEY.CONTRACT_NO "
            + "AND OVD_KND = :LNRRATL.KEY.OVD_KND "
            + "AND ACTV_DT = :LNRRATL.KEY.ACTV_DT";
        IBS.READ(SCCGWA, LNRRATL, this, LNTRATL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATL_NOTFND, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_UPD_LNTRATL() throws IOException,SQLException,Exception {
        LNTRATL_RD = new DBParm();
        LNTRATL_RD.TableName = "LNTRATL";
        IBS.REWRITE(SCCGWA, LNRRATL, LNTRATL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATL_NOTFND, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATL_NOTFND, WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZRATHM() throws IOException,SQLException,Exception {
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        IBS.WRITE(SCCGWA, LNRRATH, LNTRATH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATH_NOTFND, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_LNTEXTN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNREXTN.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNREXTN.KEY.EXT_FLG);
        CEP.TRC(SCCGWA, LNREXTN.KEY.STATUS);
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        LNTEXTN_RD.where = "CONTRACT_NO = :LNREXTN.KEY.CONTRACT_NO "
            + "AND STATUS = :LNREXTN.KEY.STATUS";
        LNTEXTN_RD.fst = true;
        IBS.READ(SCCGWA, LNREXTN, this, LNTEXTN_RD);
    }
    public void T00_READ_LNTEXTN_1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNREXTN.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNREXTN.KEY.VAL_DT);
        CEP.TRC(SCCGWA, LNREXTN.KEY.STATUS);
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        LNTEXTN_RD.where = "CONTRACT_NO = :LNREXTN.KEY.CONTRACT_NO "
            + "AND VAL_DT = :LNREXTN.KEY.VAL_DT "
            + "AND STATUS = :LNREXTN.KEY.STATUS";
        LNTEXTN_RD.fst = true;
        IBS.READ(SCCGWA, LNREXTN, this, LNTEXTN_RD);
    }
    public void T00_READ_LNTEXTN_2() throws IOException,SQLException,Exception {
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        LNTEXTN_RD.where = "CONTRACT_NO = :LNREXTN.KEY.CONTRACT_NO "
            + "AND VAL_DT = :LNREXTN.KEY.VAL_DT "
            + "AND STATUS = :LNREXTN.KEY.STATUS";
        IBS.READ(SCCGWA, LNREXTN, this, LNTEXTN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_REC_NOTFND, LNCSEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_REUPDATE_LNTEXTN() throws IOException,SQLException,Exception {
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        LNTEXTN_RD.upd = true;
        IBS.READ(SCCGWA, LNREXTN, LNTEXTN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_EXIST_FWDH_FLAG = 'Y';
        } else {
            WS_EXIST_FWDH_FLAG = 'N';
        }
    }
    public void T00_WRITE_LNTEXTN() throws IOException,SQLException,Exception {
        LNREXTN.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNREXTN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNREXTN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNREXTN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        IBS.WRITE(SCCGWA, LNREXTN, LNTEXTN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_EXTN_DUPKEY, LNCSEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_REWRITE_LNTEXTN() throws IOException,SQLException,Exception {
        LNREXTN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNREXTN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        IBS.REWRITE(SCCGWA, LNREXTN, LNTEXTN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_EXTN_DUPKEY, LNCSEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_DELETE_LNTEXTN() throws IOException,SQLException,Exception {
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        IBS.DELETE(SCCGWA, LNREXTN, LNTEXTN_RD);
    }
    public void T000_READ_LNTPPRP() throws IOException,SQLException,Exception {
        LNTPPRP_RD = new DBParm();
        LNTPPRP_RD.TableName = "LNTPPRP";
        LNTPPRP_RD.where = "CONTRACT_NO = :LNRPPRP.KEY.CONTRACT_NO "
            + "AND STATUS = :LNRPPRP.STATUS";
        IBS.READ(SCCGWA, LNRPPRP, this, LNTPPRP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PPRP_EXTN, LNCSEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_BPTNHIST() throws IOException,SQLException,Exception {
        BPRNHIST.KEY.AC_DT = LNCSEXT.VAL_DATE;
        BPRNHIST.KEY.JRNNO = LNCSEXT.JRNNO;
        BPTNHIST_RD = new DBParm();
        BPTNHIST_RD.TableName = "BPTNHIST";
        BPTNHIST_RD.where = "AC_DT = :BPRNHIST.KEY.AC_DT "
            + "AND JRNNO = :BPRNHIST.KEY.JRNNO";
        IBS.READ(SCCGWA, BPRNHIST, this, BPTNHIST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_REC_NOTFND, LNCSEXT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSEXT.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSEXT=");
            CEP.TRC(SCCGWA, LNCSEXT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
