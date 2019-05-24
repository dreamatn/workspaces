package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZBLRG {
    int JIBS_tmp_int;
    brParm CITFLRG_BR = new brParm();
    DBParm CITBAS_RD;
    DBParm CITACR_RD;
    DBParm CITFLMT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 99;
    int K_COL_STS = 9;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRFLMT CIRFLMT = new CIRFLMT();
    CIRFLRG CIRFLRG = new CIRFLRG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICOBLRG CICOBLRG = new CICOBLRG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICBLRG CICBLRG;
    public void MP(SCCGWA SCCGWA, CICBLRG CICBLRG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICBLRG = CICBLRG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZBLRG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICBLRG.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_BROW_FLRG_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICBLRG.DATA.CI_NO);
        if (CICBLRG.DATA.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICBLRG.DATA.CI_NO;
            CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
            T000_READ_CITBAS();
            if (pgmRtn) return;
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
                || CIRBAS.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                || CIRBAS.STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, "BAS INF NOT FND");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICBLRG.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (CICBLRG.DATA.SVS_ADC_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRFLMT);
            CIRFLMT.KEY.SVS_ADC_NO = CICBLRG.DATA.SVS_ADC_NO;
            CEP.TRC(SCCGWA, CIRFLMT.KEY.SVS_ADC_NO);
            T000_READ_CITFLMT();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "FLMT INF NOT FND");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FLMT_INF_NOTFND, CICBLRG.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, CICBLRG.DATA.AGR_NO);
        if (CICBLRG.DATA.AGR_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CICBLRG.DATA.AGR_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "ACR INF NOT FND");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICBLRG.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_BROW_FLRG_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRFLRG);
        CIRFLRG.CI_NO = CICBLRG.DATA.CI_NO;
        CIRFLRG.AGR_NO = CICBLRG.DATA.AGR_NO;
        CIRFLRG.SVS_ADC_NO = CICBLRG.DATA.SVS_ADC_NO;
        T000_STARTBR_CITFLRG_BY_CIACADC();
        if (pgmRtn) return;
        T000_READNEXT_CITFLRG();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FLRG_NOTFND, CICBLRG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B020_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            B020_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITFLRG();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITFLRG();
        if (pgmRtn) return;
    }
    public void B020_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_STS;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        R000_DATA_TRANS_TO_FMTMPAG();
        if (pgmRtn) return;
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOBLRG);
        SCCMPAG.DATA_LEN = 469;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_DATA_TRANS_TO_FMTMPAG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOBLRG);
        CEP.TRC(SCCGWA, CICOBLRG.DATA.CI_NO);
        CICOBLRG.DATA.AC_DATE = CIRFLRG.KEY.AC_DATE;
        CICOBLRG.DATA.JRN_NO = CIRFLRG.KEY.JRN_NO;
        CICOBLRG.DATA.SEQ = CIRFLRG.KEY.SEQ;
        CICOBLRG.DATA.CI_NO = CIRFLRG.CI_NO;
        CICOBLRG.DATA.TXN_TYP = CIRFLRG.TXN_TYP;
        CICOBLRG.DATA.AGR_NO = CIRFLRG.AGR_NO;
        CICOBLRG.DATA.ACAC_NO = CIRFLRG.ACAC_NO;
        CICOBLRG.DATA.SVS_ADC_NO = CIRFLRG.SVS_ADC_NO;
        CICOBLRG.DATA.SVS_ORG_CLS = CIRFLRG.SVS_ORG_CLS;
        CICOBLRG.DATA.RPL_SVS_ADC_NO = CIRFLRG.RPL_SVS_ADC_NO;
        CICOBLRG.DATA.TXN_CCY = CIRFLRG.TXN_CCY;
        CICOBLRG.DATA.TXN_AMT = CIRFLRG.TXN_AMT;
        CICOBLRG.DATA.LMT_TYP = CIRFLRG.LMT_TYP;
        CICOBLRG.DATA.LMT_KND = CIRFLRG.LMT_KND;
        CICOBLRG.DATA.LMT_CCY = CIRFLRG.LTM_CCY;
        CICOBLRG.DATA.BAL_LMT = CIRFLRG.BAL_LMT;
        CICOBLRG.DATA.CR_TOT_LMT_AMT = CIRFLRG.CR_TOT_LMT_AMT;
        CICOBLRG.DATA.CR_TOT_CAM = CIRFLRG.CR_TOT_CAM;
        CICOBLRG.DATA.DR_TOT_LMT_AMT = CIRFLRG.DR_TOT_LMT_AMT;
        CICOBLRG.DATA.DR_TOT_CAM = CIRFLRG.DR_TOT_CAM;
        CICOBLRG.DATA.RMK = CIRFLRG.RMK;
    }
    public void T000_STARTBR_CITFLRG_BY_CIACADC() throws IOException,SQLException,Exception {
        CITFLRG_BR.rp = new DBParm();
        CITFLRG_BR.rp.TableName = "CITFLRG";
        CITFLRG_BR.rp.where = "( CI_NO = :CIRFLRG.CI_NO "
            + "OR :CIRFLRG.CI_NO = ' ' ) "
            + "AND ( AGR_NO = :CIRFLRG.AGR_NO "
            + "OR :CIRFLRG.AGR_NO = ' ' ) "
            + "AND ( SVS_ADC_NO = :CIRFLRG.SVS_ADC_NO "
            + "OR :CIRFLRG.SVS_ADC_NO = ' ' )";
        IBS.STARTBR(SCCGWA, CIRFLRG, this, CITFLRG_BR);
    }
    public void T000_READNEXT_CITFLRG() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRFLRG, this, CITFLRG_BR);
    }
    public void T000_ENDBR_CITFLRG() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITFLRG_BR);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        CITACR_RD.where = "AGR_NO = :CIRACR.KEY.AGR_NO "
            + "AND STS < > '1'";
        IBS.READ(SCCGWA, CIRACR, this, CITACR_RD);
    }
    public void T000_READ_CITFLMT() throws IOException,SQLException,Exception {
        CITFLMT_RD = new DBParm();
        CITFLMT_RD.TableName = "CITFLMT";
        IBS.READ(SCCGWA, CIRFLMT, CITFLMT_RD);
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
