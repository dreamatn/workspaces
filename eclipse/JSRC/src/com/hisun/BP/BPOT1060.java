package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1060 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_S_MAT_ACOBL = "BP-S-MATAIN-ACOBL";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_INP_NUM = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSACOB BPCSACOB = new BPCSACOB();
    SCCGWA SCCGWA;
    BPB1060_AWA_1060 BPB1060_AWA_1060;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT1060 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1060_AWA_1060>");
        BPB1060_AWA_1060 = (BPB1060_AWA_1060) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSACOB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1060_AWA_1060.AC_FLG);
        CEP.TRC(SCCGWA, BPB1060_AWA_1060.AC_TYPE);
        CEP.TRC(SCCGWA, BPB1060_AWA_1060.CI_NO);
        CEP.TRC(SCCGWA, BPB1060_AWA_1060.ACNO_AC);
        CEP.TRC(SCCGWA, BPB1060_AWA_1060.ACNO_SEQ);
        CEP.TRC(SCCGWA, BPB1060_AWA_1060.USED_FLG);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BROWSE_SACOB();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_INP_NUM = 0;
        if (BPB1060_AWA_1060.AC_FLG != ' ') {
            CEP.TRC(SCCGWA, "TYPE_NOT_SPACE");
            WS_INP_NUM += 1;
        }
        CEP.TRC(SCCGWA, "AAA");
        CEP.TRC(SCCGWA, WS_INP_NUM);
        if (BPB1060_AWA_1060.ACNO_AC.trim().length() > 0) {
            CEP.TRC(SCCGWA, "ACNO_NOT_SPACE");
            WS_INP_NUM += 1;
        }
        CEP.TRC(SCCGWA, "BBB");
        CEP.TRC(SCCGWA, WS_INP_NUM);
        if (BPB1060_AWA_1060.ACNO_SEQ != 0) {
            CEP.TRC(SCCGWA, "ACSEQ_NOT_ZERO");
            CEP.TRC(SCCGWA, BPB1060_AWA_1060.ACNO_SEQ);
            WS_INP_NUM += 1;
        }
        CEP.TRC(SCCGWA, "CCC");
        CEP.TRC(SCCGWA, WS_INP_NUM);
        if (BPB1060_AWA_1060.CI_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "CINO_NOT_SPACE");
            WS_INP_NUM += 1;
        }
        CEP.TRC(SCCGWA, WS_INP_NUM);
        CEP.TRC(SCCGWA, "DDD");
        if (WS_INP_NUM > 1) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MORE_THAN_ONE_COND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_INP_NUM < 1) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NONE_COND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_SACOB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSACOB);
        BPCSACOB.FUNC = 'B';
        B040_TRANS_ACOBL();
        if (pgmRtn) return;
        S000_CALL_BPZSACOB();
        if (pgmRtn) return;
    }
    public void B040_TRANS_ACOBL() throws IOException,SQLException,Exception {
        BPCSACOB.DATA.AC_FLG = BPB1060_AWA_1060.AC_FLG;
        BPCSACOB.DATA.AC_TYPE = BPB1060_AWA_1060.AC_TYPE;
        BPCSACOB.DATA.CI_NO = BPB1060_AWA_1060.CI_NO;
        BPCSACOB.DATA.AC_NO = BPB1060_AWA_1060.ACNO_AC;
        BPCSACOB.DATA.AC_NO_SEQ = BPB1060_AWA_1060.ACNO_SEQ;
        BPCSACOB.DATA.USED_FLG = BPB1060_AWA_1060.USED_FLG;
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_FLG);
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_TYPE);
        CEP.TRC(SCCGWA, BPCSACOB.DATA.CI_NO);
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_NO);
        CEP.TRC(SCCGWA, BPCSACOB.DATA.AC_NO_SEQ);
        CEP.TRC(SCCGWA, BPCSACOB.DATA.USED_FLG);
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
