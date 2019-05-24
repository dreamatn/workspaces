package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZTACAC {
    DBParm CITACR_RD;
    brParm CITACRL_BR = new brParm();
    brParm CITACAC_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    String WS_AGR_NO = " ";
    String WS_AC_REL = " ";
    String WS_FRM_APP_ACR = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CICOACAC CICOACAC = new CICOACAC();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    CIRACR CIRACR = new CIRACR();
    CIRACAC CIRACAC = new CIRACAC();
    CIRACRL CIRACRL = new CIRACRL();
    int WS_AC_DATE = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICTACAC CICTACAC;
    public void MP(SCCGWA SCCGWA, CICTACAC CICTACAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICTACAC = CICTACAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZTACAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICTACAC.RC);
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_ACAC_INF();
        if (pgmRtn) return;
        B030_INQ_ACRL_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_INQ_ACAC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICTACAC.DATA.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACR INF NOT FND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICTACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_AGR_NO = CIRACR.KEY.AGR_NO;
        WS_FRM_APP_ACR = CIRACR.FRM_APP;
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        R000_INQ_ACAC_INF();
        if (pgmRtn) return;
    }
    public void B030_INQ_ACRL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.REL_AC_NO = CICTACAC.DATA.AGR_NO;
        T000_STARTBR_CITACRL_RAC();
        if (pgmRtn) return;
        T000_READNEXT_CITACRL();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CIRACRL.KEY.AC_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
            WS_AGR_NO = CIRACR.KEY.AGR_NO;
            WS_FRM_APP_ACR = CIRACR.FRM_APP;
            WS_AC_REL = CIRACRL.KEY.AC_REL;
            R000_INQ_ACAC_INF();
            if (pgmRtn) return;
            T000_READNEXT_CITACRL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACRL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.AC_NO = CICTACAC.DATA.AGR_NO;
        T000_STARTBR_CITACRL_AC();
        if (pgmRtn) return;
        T000_READNEXT_CITACRL();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CIRACRL.KEY.REL_AC_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
            WS_AGR_NO = CIRACR.KEY.AGR_NO;
            WS_FRM_APP_ACR = CIRACR.FRM_APP;
            WS_AC_REL = CIRACRL.KEY.AC_REL;
            R000_INQ_ACAC_INF();
            if (pgmRtn) return;
            T000_READNEXT_CITACRL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACRL();
        if (pgmRtn) return;
    }
    public void R000_INQ_ACAC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.AGR_NO = WS_AGR_NO;
        T000_STARTBR_CITACAC_BY_AC();
        if (pgmRtn) return;
        T000_READNEXT_CITACAC();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, CICOACAC);
            CICOACAC.DATA.AGR_NO = WS_AGR_NO;
            CICOACAC.DATA.ACAC_NO = CIRACAC.KEY.ACAC_NO;
            CICOACAC.DATA.AC_REL = WS_AC_REL;
            if (CIRACAC.FRM_APP.equalsIgnoreCase("DD")) {
                CICOACAC.DATA.CCY = CIRACAC.CCY;
                CICOACAC.DATA.CR_FLG = CIRACAC.CR_FLG;
            } else if (CIRACAC.FRM_APP.equalsIgnoreCase("TD")) {
                CICOACAC.DATA.AGR_SEQ = CIRACAC.AGR_SEQ;
                CICOACAC.DATA.BV_NO = CIRACAC.BV_NO;
            } else if (CIRACAC.FRM_APP.equalsIgnoreCase("IB")) {
                CICOACAC.DATA.AGR_SEQ = CIRACAC.AGR_SEQ;
                CICOACAC.DATA.CCY = CIRACAC.CCY;
                CICOACAC.DATA.CR_FLG = CIRACAC.CR_FLG;
            } else if (CIRACAC.FRM_APP.equalsIgnoreCase("LN")) {
                CICOACAC.DATA.AGR_SEQ = CIRACAC.AGR_SEQ;
            } else {
            }
            CICOACAC.DATA.PROD_CD = CIRACAC.PROD_CD;
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = CIRACAC.PROD_CD;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            CICOACAC.DATA.CDESC = BPCPQPRD.PRDT_NAME;
            CICOACAC.DATA.FRM_APP_ACR = WS_FRM_APP_ACR;
            CICOACAC.DATA.FRM_APP_ACAC = CIRACAC.FRM_APP;
            CICOACAC.DATA.ACAC_STS = CIRACAC.ACAC_STS;
            R000_02_OUTPUT_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITACAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACAC();
        if (pgmRtn) return;
    }
    public void R000_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_02_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOACAC);
        SCCMPAG.DATA_LEN = 172;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
    }
    public void T000_STARTBR_CITACRL_RAC() throws IOException,SQLException,Exception {
        CITACRL_BR.rp = new DBParm();
        CITACRL_BR.rp.TableName = "CITACRL";
        CITACRL_BR.rp.eqWhere = "REL_AC_NO";
        CITACRL_BR.rp.where = "( EXP_DT = 0 "
            + "OR EXP_DT > :WS_AC_DATE ) "
            + "AND AC_REL IN ( '05' , '06' , '07' , '11' )";
        IBS.STARTBR(SCCGWA, CIRACRL, this, CITACRL_BR);
    }
    public void T000_STARTBR_CITACRL_AC() throws IOException,SQLException,Exception {
        CITACRL_BR.rp = new DBParm();
        CITACRL_BR.rp.TableName = "CITACRL";
        CITACRL_BR.rp.eqWhere = "AC_NO";
        CITACRL_BR.rp.where = "( EXP_DT = 0 "
            + "OR EXP_DT > :WS_AC_DATE ) "
            + "AND AC_REL = '04'";
        IBS.STARTBR(SCCGWA, CIRACRL, this, CITACRL_BR);
    }
    public void T000_READNEXT_CITACRL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACRL, this, CITACRL_BR);
    }
    public void T000_ENDBR_CITACRL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACRL_BR);
    }
    public void T000_STARTBR_CITACAC_BY_AC() throws IOException,SQLException,Exception {
        CITACAC_BR.rp = new DBParm();
        CITACAC_BR.rp.TableName = "CITACAC";
        CITACAC_BR.rp.eqWhere = "AGR_NO";
        CITACAC_BR.rp.where = "SUBSTR ( ACAC_CTL , 1 , 1 ) = '0' "
            + "AND AID = ' ' "
            + "AND ACAC_STS < > 2";
        CITACAC_BR.rp.order = "AGR_SEQ";
        IBS.STARTBR(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_READNEXT_CITACAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACAC, this, CITACAC_BR);
    }
    public void T000_ENDBR_CITACAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACAC_BR);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
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
