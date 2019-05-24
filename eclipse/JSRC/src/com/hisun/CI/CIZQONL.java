package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZQONL {
    int BAS_CI_NM_LEN;
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    DBParm CITACR_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CICFB51 CICFB51 = new CICFB51();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICQONL CICQONL;
    public void MP(SCCGWA SCCGWA, CICQONL CICQONL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICQONL = CICQONL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZQONL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICQONL.RC);
        IBS.init(SCCGWA, CICFB51);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_CUST_ONL();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQONL.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICQONL.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICQONL.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICQONL.DATA.CI_NM);
        CEP.TRC(SCCGWA, CICQONL.DATA.AGR_NO);
        if (CICQONL.DATA.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICQONL.DATA.CI_NO;
            T000_READ_CITBAS();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICQONL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else if (CICQONL.DATA.ID_TYPE.trim().length() > 0 
                && CICQONL.DATA.ID_NO.trim().length() > 0 
                && CICQONL.DATA.CI_NM.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.ID_TYPE = CICQONL.DATA.ID_TYPE;
            CIRBAS.ID_NO = CICQONL.DATA.ID_NO;
            CIRBAS.CI_NM = CICQONL.DATA.CI_NM;
            BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
            T000_READ_CITBAS_BY_IDNM();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICQONL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else if (CICQONL.DATA.AGR_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CICQONL.DATA.AGR_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICQONL.RC);
            }
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CIRACR.CI_NO;
            T000_READ_CITBAS();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICQONL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_INQ_CUST_ONL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
        IBS.init(SCCGWA, CICFB51);
        CICFB51.CI_NO = CIRBAS.KEY.CI_NO;
        CICFB51.ID_TYPE = CIRBAS.ID_TYPE;
        CICFB51.ID_NO = CIRBAS.ID_NO;
        if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
        JIBS_tmp_int = CIRBAS.CI_NM.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
        CICFB51.CI_NM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CIRBAS.KEY.CI_NO;
        T000_READ_CITACR_CHKONL();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICFB51.ONL_FLG = 'N';
        } else {
            CICFB51.ONL_FLG = 'Y';
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CIB51";
        SCCFMT.DATA_PTR = CICFB51;
        SCCFMT.DATA_LEN = 342;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITBAS_BY_IDNM() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.eqWhere = "ID_TYPE,ID_NO,CI_NM";
        CITBAS_RD.where = "SUBSTR ( STSW , 3 , 1 ) = '0' "
            + "AND SUBSTR ( STSW , 23 , 1 ) = '0'";
        IBS.READ(SCCGWA, CIRBAS, this, CITBAS_RD);
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
    }
    public void T000_READ_CITACR_CHKONL() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        CITACR_RD.eqWhere = "CI_NO";
        CITACR_RD.where = "PROD_CD < > '6606097200'";
        CITACR_RD.fst = true;
        IBS.READ(SCCGWA, CIRACR, this, CITACR_RD);
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
