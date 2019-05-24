package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSSCE {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTAPRD_RD;
    boolean pgmRtn = false;
    String K_HIS_RMKS = " ";
    String WS_ERR_MSG = " ";
    char WS_APRD_PAY_MTH = ' ';
    char WS_STS = ' ';
    char WS_RTN_INT_FLG = ' ';
    char WS_READ_LNTAPRD_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNRICTL LNRICTL = new LNRICTL();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCRCAL LNCRCAL = new LNCRCAL();
    LNCICAL LNCICAL = new LNCICAL();
    LNCPLAJ LNCPLAJ = new LNCPLAJ();
    LNRAPRD LNRAPRD = new LNRAPRD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    LNCSSCE LNCSSCE;
    public void MP(SCCGWA SCCGWA, LNCSSCE LNCSSCE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSSCE = LNCSSCE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSSCE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_PROC();
        if (pgmRtn) return;
        B020_REV_CAL_PROC();
        if (pgmRtn) return;
        B030_MAIN_PROC();
        if (pgmRtn) return;
        B040_PLAJ_PROC();
        if (pgmRtn) return;
        B050_INT_CAL_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_PROC() throws IOException,SQLException,Exception {
        if (LNCSSCE.SC_DT == 0) {
            LNCSSCE.SC_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        WS_STS = LNCSSCE.STS;
        if ((WS_STS != 'Y' 
            && WS_STS != 'N')) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCSSCE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSSCE.CTA.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCSSCE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNCSSCE.CTA;
        LNCRCONT.FUNC = 'I';
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSSCE.SC_DT);
        CEP.TRC(SCCGWA, LNRCONT.KEY.CONTRACT_NO);
        if (LNCSSCE.SC_DT < LNRCONT.LAST_F_VAL_DATE 
            && LNCSSCE.STS == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LN1659, LNCSSCE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSSCE.SC_DT > SCCGWA.COMM_AREA.AC_DATE 
            && LNCSSCE.STS == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LN1687, LNCSSCE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNRCONT.CONTRACT_TYPE.equalsIgnoreCase("CLGU")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_PROD_CAN_NOT_CLGU, LNCSSCE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSSCE.SC_DT < LNRCONT.START_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SC_DT, LNCSSCE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCRICTL);
        IBS.init(SCCGWA, LNRICTL);
        LNCRICTL.FUNC = 'I';
        LNRICTL.KEY.CONTRACT_NO = LNCSSCE.CTA;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LOAN_STS, LNCSSCE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LOAN_STS, LNCSSCE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNCSSCE.STS == 'Y' 
            && LNRICTL.CTL_STSW.substring(15 - 1, 15 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, "11111111111");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_NY_STS, LNCSSCE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNCSSCE.STS == 'N' 
            && !LNRICTL.CTL_STSW.substring(15 - 1, 15 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, "222222222222");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_NY_STS2, LNCSSCE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_REV_CAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCAL);
        WS_RTN_INT_FLG = 'N';
        if (LNCSSCE.SC_DT < SCCGWA.COMM_AREA.AC_DATE) {
            LNCRCAL.COMM_DATA.FUNC_CODE = 'U';
            LNCRCAL.COMM_DATA.FUNC_TYPE = 'I';
            LNCRCAL.COMM_DATA.LN_AC = LNCSSCE.CTA;
            LNCRCAL.COMM_DATA.SUF_NO = "" + 0;
            JIBS_tmp_int = LNCRCAL.COMM_DATA.SUF_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) LNCRCAL.COMM_DATA.SUF_NO = "0" + LNCRCAL.COMM_DATA.SUF_NO;
            LNCRCAL.COMM_DATA.VAL_DATE = LNCSSCE.SC_DT;
            WS_RTN_INT_FLG = 'Y';
            S000_CALL_LNZRCAL();
            if (pgmRtn) return;
        }
    }
    public void B030_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSSCE.SC_DT);
        if (LNCSSCE.STS == 'Y') {
            CEP.TRC(SCCGWA, "SSCE-STS = Y ");
            IBS.init(SCCGWA, LNCRICTL);
            IBS.init(SCCGWA, LNRICTL);
            LNCRICTL.FUNC = 'R';
            LNRICTL.KEY.CONTRACT_NO = LNCSSCE.CTA;
            LNRICTL.KEY.SUB_CTA_NO = 0;
            S000_CALL_LNZRICTL();
            if (pgmRtn) return;
            LNCRICTL.FUNC = 'U';
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            LNRICTL.CTL_STSW = LNRICTL.CTL_STSW.substring(0, 15 - 1) + "1" + LNRICTL.CTL_STSW.substring(15 + 1 - 1);
            S000_CALL_LNZRICTL();
            if (pgmRtn) return;
            IBS.init(SCCGWA, LNCLOANM);
            LNCLOANM.FUNC = '4';
            LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCSSCE.CTA;
            S000_CALL_LNZLOANM();
            if (pgmRtn) return;
            if (LNCLOANM.REC_DATA.EMBEZ_DATE != 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LN1660, LNCSSCE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            LNCLOANM.FUNC = '2';
            LNCLOANM.REC_DATA.EMBEZ_DATE = LNCSSCE.SC_DT;
            S000_CALL_LNZLOANM();
            if (pgmRtn) return;
        }
        if (LNCSSCE.STS == 'N') {
            CEP.TRC(SCCGWA, "SSCE-STS : N ");
            IBS.init(SCCGWA, LNCRICTL);
            IBS.init(SCCGWA, LNRICTL);
            LNCRICTL.FUNC = 'R';
            LNRICTL.KEY.CONTRACT_NO = LNCSSCE.CTA;
            LNRICTL.KEY.SUB_CTA_NO = 0;
            S000_CALL_LNZRICTL();
            if (pgmRtn) return;
            LNCRICTL.FUNC = 'U';
            if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
            JIBS_tmp_int = LNRICTL.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
            LNRICTL.CTL_STSW = LNRICTL.CTL_STSW.substring(0, 15 - 1) + "0" + LNRICTL.CTL_STSW.substring(15 + 1 - 1);
            S000_CALL_LNZRICTL();
            if (pgmRtn) return;
            IBS.init(SCCGWA, LNCLOANM);
            LNCLOANM.FUNC = '4';
            LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCSSCE.CTA;
            S000_CALL_LNZLOANM();
            if (pgmRtn) return;
            if (LNCSSCE.SC_DT < LNCLOANM.REC_DATA.EMBEZ_DATE) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SC_DT_ERR, LNCSSCE.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                LNCLOANM.FUNC = '2';
                LNCLOANM.REC_DATA.EMBEZ_CANCEL_DATE = LNCSSCE.SC_DT;
                S000_CALL_LNZLOANM();
                if (pgmRtn) return;
            }
        }
        R000_WTRITE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B040_PLAJ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLAJ);
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNCSSCE.CTA;
        T000_READ_LNTAPRD();
        if (pgmRtn) return;
        if (LNRAPRD.PAY_MTH == '4' 
            && LNRICTL.CUR_FO_RAT > LNRICTL.CUR_RAT) {
            LNCPLAJ.COMM_DATA.ADJ_IND = 'R';
            LNCPLAJ.COMM_DATA.LN_AC = LNCSSCE.CTA;
            LNCPLAJ.COMM_DATA.SUF_NO = "" + 0;
            JIBS_tmp_int = LNCPLAJ.COMM_DATA.SUF_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) LNCPLAJ.COMM_DATA.SUF_NO = "0" + LNCPLAJ.COMM_DATA.SUF_NO;
            S000_CALL_LNZPLAJ();
            if (pgmRtn) return;
        }
    }
    public void B050_INT_CAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICAL);
        if (WS_RTN_INT_FLG == 'Y') {
            LNCICAL.COMM_DATA.FUNC_CODE = 'U';
            LNCICAL.COMM_DATA.FUNC_TYPE = 'I';
            LNCICAL.COMM_DATA.LN_AC = LNCSSCE.CTA;
            LNCICAL.COMM_DATA.SUF_NO = "" + 0;
            JIBS_tmp_int = LNCICAL.COMM_DATA.SUF_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) LNCICAL.COMM_DATA.SUF_NO = "0" + LNCICAL.COMM_DATA.SUF_NO;
            LNCICAL.COMM_DATA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            S000_CALL_LNZICAL();
            if (pgmRtn) return;
        }
    }
    public void R000_WTRITE_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.REF_NO = LNCSSCE.CTA;
        BPCPNHIS.INFO.AC = LNCSSCE.CTA;
        BPCPNHIS.INFO.CI_NO = LNCSSCE.CI_NO;
        BPCPNHIS.INFO.FMT_ID = "LNCHUEXT";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        if (LNCSSCE.STS == 'Y') {
            K_HIS_RMKS = "SET STOP RATN DATE";
        } else {
            K_HIS_RMKS = "CAN STOP RATN DATE";
        }
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMKS;
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID_LEN = 379;
        BPCPNHIS.INFO.OLD_DAT_PT = LNCSSCE;
        BPCPNHIS.INFO.NEW_DAT_PT = LNCSSCE;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSSCE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTAPRD() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        IBS.READ(SCCGWA, LNRAPRD, LNTAPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_APRD_NOTFND, LNCSSCE.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTAPRD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RETURN_INFO != 'F') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSSCE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRICTL() throws IOException,SQLException,Exception {
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 425;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        if (LNCRICTL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRICTL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSSCE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSSCE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-REV-CAL-BASE", LNCRCAL);
        if (LNCRCAL.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRCAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSSCE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-INT-CAL", LNCICAL);
        if (LNCICAL.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSSCE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPLAJ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-PLPI-AUTADJ", LNCPLAJ);
        if (LNCPLAJ.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPLAJ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSSCE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
        if (LNCSSCE.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSSCE=");
            CEP.TRC(SCCGWA, LNCSSCE);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
