package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFBVDT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_REC_BPRD = "BP-R-BRW-BVPRD      ";
    String K_HIS_REMARKS = "BV PRODUCT PARM MAINTAIN";
    String K_CPY_BPRPBPRD = "BPRPBPRD";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZFBVDT_WS_BPRD_KEY WS_BPRD_KEY = new BPZFBVDT_WS_BPRD_KEY();
    char WS_TBL_BPRD_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRPBPRD BPRPBPRD = new BPRPBPRD();
    BPRPBPRD BPROBPRD = new BPRPBPRD();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCOBVDT BPCOBVDT = new BPCOBVDT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRBPRD BPRBPRD = new BPRBPRD();
    BPCRBPRD BPCRBPRD = new BPCRBPRD();
    SCCGWA SCCGWA;
    BPCFBVDT BPCFBVDT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFBVDT BPCFBVDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFBVDT = BPCFBVDT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFBVDT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_QUERY_PROCESS();
        if (pgmRtn) return;
        B020_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBPRD);
        IBS.init(SCCGWA, BPRBPRD);
        if (BPCFBVDT.IBS_AC_BK.trim().length() == 0) {
            BPRBPRD.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        } else {
            BPRBPRD.KEY.IBS_AC_BK = BPCFBVDT.IBS_AC_BK;
        }
        WS_BPRD_KEY.WS_BPRD_BR = BPCFBVDT.BR;
        WS_BPRD_KEY.WS_BPRD_CODE = BPCFBVDT.CODE;
        BPCPRMM.EFF_DT = BPCFBVDT.EFF_DATE;
        BPRBPRD.KEY.CODE = IBS.CLS2CPY(SCCGWA, WS_BPRD_KEY);
        BPCRBPRD.INFO.FUNC = 'R';
        BPCRBPRD.INFO.POINTER = BPRBPRD;
        BPCRBPRD.INFO.LEN = 512;
        S000_CALL_BPZRBPRD();
        if (pgmRtn) return;
        if (BPCRBPRD.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BPRD_NOTFND, BPCFBVDT.RC);
        }
    }
    public void B020_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_OUTPUT_CH();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_OUTPUT_CH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRPBPRD.DATA_TXT.COST_PRICE);
        CEP.TRC(SCCGWA, BPRPBPRD.DATA_TXT.SER_CHARGE);
        IBS.init(SCCGWA, BPCOBVDT);
        BPCFBVDT.BV_CNM = BPRPBPRD.DATA_TXT.BV_CNM;
        BPCFBVDT.BV_ENM = BPRPBPRD.DATA_TXT.BV_ENM;
        BPCFBVDT.BV_CNMS = BPRPBPRD.DATA_TXT.BV_CNMS;
        BPCFBVDT.BV_ENMS = BPRPBPRD.DATA_TXT.BV_ENMS;
        BPCFBVDT.EFF_DATE = BPCPRMM.EFF_DT;
        BPCFBVDT.EXP_DATE = BPCPRMM.EXP_DT;
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_MT, BPCPRMM);
    }
    public void S000_CALL_BPZRBPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_BPRD, BPCRBPRD);
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
