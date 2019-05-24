package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZOSINT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC         ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    BPROSINT BPROSINT = new BPROSINT();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCGWA SCCGWA;
    BPCOSINT BPCOSINT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOSINT BPCOSINT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOSINT = BPCOSINT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZOSINT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPROSINT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOSINT.FUNC);
        if (BPCOSINT.FUNC == 'A') {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCOSINT.VAL == null) BPCOSINT.VAL = "";
        JIBS_tmp_int = BPCOSINT.VAL.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) BPCOSINT.VAL += " ";
        IBS.CPY2CLS(SCCGWA, BPCOSINT.VAL.substring(0, BPCOSINT.LEN), BPROSINT.DATA_TXT);
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = "RBASE";
        CEP.TRC(SCCGWA, BPCOQPCD.INPUT_DATA.TYPE);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPROSINT.DATA_TXT);
        BPCOQPCD.INPUT_DATA.CODE = JIBS_tmp_str[0];
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPROSINT.DATA_TXT);
        CEP.TRC(SCCGWA, JIBS_tmp_str[0]);
        CEP.TRC(SCCGWA, BPCOQPCD.INPUT_DATA.CODE);
        S000_CALL_BPZPQPCD();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPREXRT;
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOQPCD.INPUT_DATA.TYPE);
        CEP.TRC(SCCGWA, BPCOQPCD.INPUT_DATA.CODE);
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        CEP.TRC(SCCGWA, BPCOQPCD.RC.RC_CODE);
        CEP.TRC(SCCGWA, BPCOQPCD.RC);
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
        if (BPCOSINT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCOSINT = ");
            CEP.TRC(SCCGWA, BPCOSINT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
