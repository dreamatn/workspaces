package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSCSTS {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTCDDAT_RD;
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    long WS_JRN_NO = 0;
    String WS_SOCIAL_CARD_NO = " ";
    char WS_SOCIAL_CARD_FLG = 'N';
    char WS_SOCIAL_LOST_FLG = 'N';
    String WS_LOST_NO = " ";
    int WS_TR_DT = 0;
    int WS_CNT2 = 0;
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRINRCD DCRINRCD = new DCRINRCD();
    DCRACLNK DCRACLNK = new DCRACLNK();
    DCRIAMST DCRIAMST = new DCRIAMST();
    DCCURHLD DCCURHLD = new DCCURHLD();
    DCRCPARM DCRCPARM = new DCRCPARM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCPLOSS BPCPLOSS = new BPCPLOSS();
    BPRLOSS BPRLOSS = new BPRLOSS();
    BPCSLOSS BPCSLOSS = new BPCSLOSS();
    BPCO1821_OPT_1821 BPCO1821_OPT_1821 = new BPCO1821_OPT_1821();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSCSTS DCCSCSTS;
    public void MP(SCCGWA SCCGWA, DCCSCSTS DCCSCSTS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSCSTS = DCCSCSTS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSCSTS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSCSTS);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_CHECK_CARD_STSW();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCCSCSTS.CARD_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO, DCCSCSTS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_CHECK_CARD_STSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSCSTS.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1") 
            && !DCRCDDAT.PROD_CD.equalsIgnoreCase("1203010101")) {
            if (DCRCDDAT.MOVE_FLG == 'Y' 
                && DCRCDDAT.ACNO_TYPE == ' ') {
            } else {
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
                    IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND, DCCSCSTS.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                IBS.init(SCCGWA, BPCPLOSS);
                BPCPLOSS.INFO.FUNC = 'I';
                BPCPLOSS.INFO.INDEX_FLG = "2";
                BPCPLOSS.DATA_INFO.AC = DCCSCSTS.CARD_NO;
                BPCPLOSS.DATA_INFO.STS = '2';
                S000_CALL_BPZPLOSS();
                if (pgmRtn) return;
                if (WS_TBL_FLAG == 'Y') {
                    IBS.init(SCCGWA, SCCCLDT);
                    WS_LOST_NO = BPCPLOSS.DATA_INFO.LOS_NO;
                    if (BPCPLOSS.DATA_INFO.LOS_DT == 0) {
                        SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
                    } else {
                        SCCCLDT.DATE1 = BPCPLOSS.DATA_INFO.LOS_DT;
                    }
                    SCCCLDT.DATE2 = SCCGWA.COMM_AREA.AC_DATE;
                    CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.LOS_DT);
                    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
                    SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
                    SCSSCLDT1.MP(SCCGWA, SCCCLDT);
                    if (SCCCLDT.RC != 0) {
                        IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERR, DCCSCSTS.RC);
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
                    CEP.TRC(SCCGWA, SCCCLDT.DAYS);
                    CEP.TRC(SCCGWA, DCRCPARM.DATA_TXT.LOS_TMP_DAYS);
                    if (SCCCLDT.DAYS < DCRCPARM.DATA_TXT.LOS_TMP_DAYS) {
                        IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS, DCCSCSTS.RC);
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    } else {
                        DCCSCSTS.ORAL_LOST_FLG = 'N';
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, DCCSCSTS.ORAL_LOST_FLG);
        CEP.TRC(SCCGWA, DCCSCSTS.FROZEN_FLG);
        CEP.TRC(SCCGWA, DCCSCSTS.FROZEN_FLG);
        CEP.TRC(SCCGWA, DCCSCSTS.ORAL_LOST_FLG);
        if (DCCSCSTS.ORAL_LOST_FLG == 'N') {
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DCCSCSTS.CARD_NO;
            T000_READ_DCTCDDAT_UPD();
            if (pgmRtn) return;
            if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
            JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
            DCRCDDAT.CARD_STSW = "0" + DCRCDDAT.CARD_STSW.substring(1);
            DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRCDDAT.UPDTBL_TLR = "BAT0001";
            T000_REWRITE_DCTCDDAT();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCSLOSS);
            BPCSLOSS.LOS_NO = WS_LOST_NO;
            BPCSLOSS.FUNC = 'U';
            BPCSLOSS.STS = '4';
            BPCSLOSS.RLOS_ORG = 706660888;
            BPCSLOSS.RLOS_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPCSLOSS.RLOS_TIME = SCCGWA.COMM_AREA.TR_TIME;
            BPCSLOSS.RLOS_TLR = "BAT0001";
            S000_CALL_BPZSLOSS();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSCSTS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.col = "CARD_NO, ACNO_TYPE, EXC_CARD_TMS, CARD_CLS_PROD, BV_CD_NO, CARD_LNK_TYP, PROD_CD, CARD_OWN_CINO, CARD_HLDR_CINO, CARD_MEDI, CARD_STS, CARD_STSW, CARD_TYP, TRAN_PIN_DAT, QURY_PIN_DAT, PVK_TYP, HOLD_AC_FLG, PROD_LMT_FLG, CVV_FLG, SAME_NAME_TFR_FLG, DIFF_NAME_TFR_FLG, DRAW_OVER_FLG, SF_FLG, DOUBLE_FREE_FLG, JOIN_CUS_FLG, ANNUAL_FEE_FREE, ISSU_BR, CLT_BR, EMBS_DT, ISSU_DT, EXP_DT, CLO_DT, ANU_FEE_NXT, ANU_FEE_PCT, ANU_FEE_FREE, ANU_FEE_ARG, PIN_ERR_CNT, PIN_LCK_DT, CVV_LCK_DT, LAST_TXN_DT, AC_OIC_NO, AC_OIC_CODE, SUB_DP, MOVE_FLG, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND, DCCSCSTS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT_UPD() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND, DCCSCSTS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void S000_CALL_BPZPLOSS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-LOSS-INFO", BPCPLOSS);
        CEP.TRC(SCCGWA, BPCPLOSS.RC);
        if (BPCPLOSS.RC.RC_CODE == 0) {
            WS_TBL_FLAG = 'Y';
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPLOSS.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase("BP1993")) {
            WS_TBL_FLAG = 'N';
        }
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPLOSS.RC);
        if (BPCPLOSS.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase("BP1993")) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPLOSS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSLOSS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-LOSS-INFO", BPCSLOSS);
        CEP.TRC(SCCGWA, BPCSLOSS.RC);
        if (BPCSLOSS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSLOSS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCSCSTS.RC);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
