package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUBVIQ {
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTDCCLS_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DCXXX";
    String WS_ERR_MSG = " ";
    String WS_PARM_CODE = " ";
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRDCCLS DCRDCCLS = new DCRDCCLS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCUBVIQ DCCUBVIQ;
    public void MP(SCCGWA SCCGWA, DCCUBVIQ DCCUBVIQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUBVIQ = DCCUBVIQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUBVIQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_QUERY_PROC();
        if (pgmRtn) return;
    }
    public void B010_QUERY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUBVIQ.CARD_PRDT_CODE);
        CEP.TRC(SCCGWA, DCCUBVIQ.CARD_CLS_CD);
        if (DCCUBVIQ.CARD_PRDT_CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_CD_MUST_INPUT, DCCUBVIQ.RC);
        }
        if (DCCUBVIQ.CARD_CLS_CD.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_CLS_CD_MISSING, DCCUBVIQ.RC);
        }
        WS_PARM_CODE = DCCUBVIQ.CARD_PRDT_CODE;
        IBS.init(SCCGWA, DCRDCCLS);
        DCRDCCLS.KEY.CARD_PROD_CD = WS_PARM_CODE;
        DCRDCCLS.KEY.CARD_CLS_CD = DCCUBVIQ.CARD_CLS_CD;
        T000_READ_DCTDCCLS();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND, DCCUBVIQ.RC);
        }
        CEP.TRC(SCCGWA, DCRDCCLS.BV_CD_NO);
        DCCUBVIQ.BV_CD_NO = DCRDCCLS.BV_CD_NO;
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCUBVIQ.RC);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_DCTDCCLS() throws IOException,SQLException,Exception {
        DCTDCCLS_RD = new DBParm();
        DCTDCCLS_RD.TableName = "DCTDCCLS";
        DCTDCCLS_RD.col = "BV_CD_NO";
        IBS.READ(SCCGWA, DCRDCCLS, DCTDCCLS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        }
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
