package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9391 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    BPOT9391_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT9391_WS_TEMP_VARIABLE();
    char WS_CI_RSVD_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    BPRHSEQ BPRHSEQ = new BPRHSEQ();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCQHSEQ BPCQHSEQ = new BPCQHSEQ();
    SCCGWA SCCGWA;
    BPB1310_AWA_1310 BPB1310_AWA_1310;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT9391 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1310_AWA_1310>");
        BPB1310_AWA_1310 = (BPB1310_AWA_1310) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_BROWSE_CINO_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.CI_NO);
        if (BPB1310_AWA_1310.CI_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CI_NO_MUST_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_BROWSE_CINO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 8;
        SCCMPAG.SCR_ROW_CNT = 50;
        SCCMPAG.SCR_COL_CNT = 100;
        B_MPAG();
        if (pgmRtn) return;
        if (BPB1310_AWA_1310.CI_NO.trim().length() == 0) WS_TEMP_VARIABLE.WS_CINO_NUM = 0;
        else WS_TEMP_VARIABLE.WS_CINO_NUM = Integer.parseInt(BPB1310_AWA_1310.CI_NO);
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_CINO_NUM);
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 1500 
            && SCCMPAG.FUNC != 'E'; WS_TEMP_VARIABLE.WS_I += 1) {
            WS_TEMP_VARIABLE.WS_CINO = "" + WS_TEMP_VARIABLE.WS_CINO_NUM;
            JIBS_tmp_int = WS_TEMP_VARIABLE.WS_CINO.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) WS_TEMP_VARIABLE.WS_CINO = "0" + WS_TEMP_VARIABLE.WS_CINO;
            B200_10_CHECK_RESERVED_PROC();
            if (pgmRtn) return;
            if (WS_CI_RSVD_FLG == 'N') {
                IBS.init(SCCGWA, SCCMPAG);
                SCCMPAG.FUNC = 'D';
                SCCMPAG.DATA_PTR = WS_TEMP_VARIABLE.WS_CINO_NUM;
                SCCMPAG.DATA_LEN = 8;
                B_MPAG();
                if (pgmRtn) return;
            }
            WS_TEMP_VARIABLE.WS_CINO_NUM += 1;
        }
    }
    public void B200_10_CHECK_RESERVED_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQHSEQ);
        IBS.init(SCCGWA, BPRHSEQ);
        BPRHSEQ.CI_NO = WS_TEMP_VARIABLE.WS_CINO;
        BPCQHSEQ.INFO.FUNC = 'C';
        BPCQHSEQ.INFO.POINTER = BPRHSEQ;
        BPCQHSEQ.LEN = 558;
        S000_CALL_BPZQHSEQ();
        if (pgmRtn) return;
        if (BPCQHSEQ.RETURN_INFO != 'N') {
            CEP.TRC(SCCGWA, "THE CINO HAD BEEN RESERVED:");
            WS_CI_RSVD_FLG = 'Y';
        } else {
            CEP.TRC(SCCGWA, "THE CINO IS NOT RESERVED:");
            WS_CI_RSVD_FLG = 'N';
        }
    }
    public void S000_CALL_BPZQHSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-HSEQ-INQ", BPCQHSEQ);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
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
