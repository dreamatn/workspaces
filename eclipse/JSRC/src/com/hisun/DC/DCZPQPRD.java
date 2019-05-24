package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZPQPRD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCCUPRCD DCCUPRCD = new DCCUPRCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCPQPRD DCCPQPRD;
    public void MP(SCCGWA SCCGWA, DCCPQPRD DCCPQPRD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCPQPRD = DCCPQPRD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZPQPRD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_QUERY_PROCESS();
        if (pgmRtn) return;
        B090_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCCPQPRD.VAL.KEY.PROD_CD.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_CD_MUST_INPUT, DCCPQPRD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRPRDPR);
        IBS.init(SCCGWA, DCCUPRCD);
        DCCUPRCD.TX_TYPE = 'I';
        DCCUPRCD.DATA.VAL.PRDMO_CD = "CARD";
        DCCUPRCD.DATA.KEY.CD.PARM_CODE = DCCPQPRD.VAL.KEY.PROD_CD;
        S000_CALL_DCZUPRCD();
        if (pgmRtn) return;
        if (DCCUPRCD.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUPRCD.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUPRCD.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRPRDPR);
            CEP.TRC(SCCGWA, DCRPRDPR);
        }
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRPRDPR.DATA_TXT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCRPRDPR.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCPQPRD.VAL.DATA);
        CEP.TRC(SCCGWA, DCCPQPRD.VAL.DATA);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = DCRPRDPR;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PROD_NOTFND, DCCPQPRD.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCPQPRD.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_DCZUPRCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-MPRD", DCCUPRCD);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
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
        if (DCCPQPRD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCPQPRD=");
            CEP.TRC(SCCGWA, DCCPQPRD);
        }
    } //FROM #ENDIF
        CEP.TRC(SCCGWA, DCCPQPRD.RC);
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
