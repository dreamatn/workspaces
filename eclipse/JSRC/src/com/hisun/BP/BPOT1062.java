package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1062 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_S_MAT_ACOBL = "BP-S-MATAIN-ACOBL";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSACOB BPCSACOB = new BPCSACOB();
    SCCGWA SCCGWA;
    BPB1062_AWA_1062 BPB1062_AWA_1062;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT1062 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1062_AWA_1062>");
        BPB1062_AWA_1062 = (BPB1062_AWA_1062) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSACOB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1062_AWA_1062.AC_FLG);
        CEP.TRC(SCCGWA, BPB1062_AWA_1062.AC_TYPE);
        CEP.TRC(SCCGWA, BPB1062_AWA_1062.ACNO_SEQ);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_READ_ACOBL();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB1062_AWA_1062.AC_FLG == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_TYPE_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB1062_AWA_1062.AC_TYPE == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_TYPE_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB1062_AWA_1062.ACNO_SEQ == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_E_NO_AC_SEQ;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_READ_ACOBL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSACOB);
        BPCSACOB.FUNC = 'R';
        B040_TRANS_ACOBL();
        if (pgmRtn) return;
        S000_CALL_BPZSACOB();
        if (pgmRtn) return;
    }
    public void B040_TRANS_ACOBL() throws IOException,SQLException,Exception {
        BPCSACOB.DATA.AC_FLG = BPB1062_AWA_1062.AC_FLG;
        BPCSACOB.DATA.AC_TYPE = BPB1062_AWA_1062.AC_TYPE;
        BPCSACOB.DATA.AC_NO_SEQ = BPB1062_AWA_1062.ACNO_SEQ;
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_FLG);
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_TYPE);
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_NO_SEQ);
    }
    public void S000_CALL_BPZSACOB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_MAT_ACOBL, BPCSACOB);
        CEP.TRC(SCCGWA, BPCSACOB.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSACOB.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSACOB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
