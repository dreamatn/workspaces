package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1202 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_S_INQ_HIST = "BP-S-INQ-HIST";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSFEEC BPCSFEEC = new BPCSFEEC();
    SCCGWA SCCGWA;
    BPC1202 BPC1202;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT1202 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPC1202 = new BPC1202();
        IBS.init(SCCGWA, BPC1202);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC1202);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_FEE_CANCEL();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B020_FEE_CANCEL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPC1202.AC_DT);
        CEP.TRC(SCCGWA, BPC1202.JRN_NO);
        CEP.TRC(SCCGWA, BPC1202.JRN_SEQ);
        CEP.TRC(SCCGWA, BPC1202.FEE_CAL_TYP);
        CEP.TRC(SCCGWA, BPC1202.FEE_CAL_AC);
        IBS.init(SCCGWA, BPCSFEEC);
        BPCSFEEC.AC_DT = BPC1202.AC_DT;
        BPCSFEEC.JRN_NO = BPC1202.JRN_NO;
        BPCSFEEC.JRN_SEQ = BPC1202.JRN_SEQ;
        BPCSFEEC.FEE_CAL_TYP = BPC1202.FEE_CAL_TYP;
        BPCSFEEC.FEE_CAL_AC = BPC1202.FEE_CAL_AC;
        CEP.TRC(SCCGWA, BPCSFEEC.AC_DT);
        CEP.TRC(SCCGWA, BPCSFEEC.JRN_NO);
        CEP.TRC(SCCGWA, BPCSFEEC.JRN_SEQ);
        CEP.TRC(SCCGWA, BPCSFEEC.FEE_CAL_TYP);
        CEP.TRC(SCCGWA, BPCSFEEC.FEE_CAL_AC);
        S000_CALL_BPZSFEEC();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZSFEEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-FEE-CANCEL", BPCSFEEC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
