package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9292 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_ACNO = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPRHSEQ BPRHSEQ = new BPRHSEQ();
    BPCRHSEB BPCRHSEB = new BPCRHSEB();
    BPCQHSEQ BPCQHSEQ = new BPCQHSEQ();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB9292_AWA_9292 BPB9292_AWA_9292;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT9292 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9292_AWA_9292>");
        BPB9292_AWA_9292 = (BPB9292_AWA_9292) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "INPUT DATA:");
        CEP.TRC(SCCGWA, BPB9292_AWA_9292.CINO);
        CEP.TRC(SCCGWA, BPB9292_AWA_9292.FLG);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BROWSE_PROC();
        if (pgmRtn) return;
        B030_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB9292_AWA_9292.CINO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CI_NO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = BPB9292_AWA_9292.CINO;
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
    }
    public void B020_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRHSEB);
        IBS.init(SCCGWA, BPRHSEQ);
        BPRHSEQ.KEY.TYPE = "ACNO";
        BPRHSEQ.CI_NO = BPB9292_AWA_9292.CINO;
        BPCRHSEB.INFO.FUNC = 'S';
        BPCRHSEB.INFO.OPT = '9';
        BPCRHSEB.INFO.POINTER = BPRHSEQ;
        BPCRHSEB.LEN = 558;
        CEP.TRC(SCCGWA, BPRHSEQ.KEY.TYPE);
        CEP.TRC(SCCGWA, BPRHSEQ.AC_NO);
        S000_CALL_BPZRHSEB();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 32;
        SCCMPAG.SCR_ROW_CNT = 50;
        SCCMPAG.SCR_COL_CNT = 100;
        B_MPAG();
        if (pgmRtn) return;
        BPCRHSEB.INFO.FUNC = 'R';
        S000_CALL_BPZRHSEB();
        if (pgmRtn) return;
        while (BPCRHSEB.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, BPRHSEQ.USED_FLAG);
            if (BPRHSEQ.USED_FLAG != 'Y') {
                WS_ACNO = BPRHSEQ.AC_NO;
                CEP.TRC(SCCGWA, WS_ACNO);
                IBS.init(SCCGWA, SCCMPAG);
                SCCMPAG.FUNC = 'D';
                SCCMPAG.DATA_PTR = WS_ACNO;
                SCCMPAG.DATA_LEN = 32;
                B_MPAG();
                if (pgmRtn) return;
            }
            BPCRHSEB.INFO.FUNC = 'R';
            S000_CALL_BPZRHSEB();
            if (pgmRtn) return;
        }
        BPCRHSEB.INFO.FUNC = 'E';
        S000_CALL_BPZRHSEB();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRHSEB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-HSEQ-BRW", BPCRHSEB);
        if (BPCRHSEB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRHSEB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
    }
    public void S000_CALL_BPZQHSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-HSEQ-INQ", BPCQHSEQ);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
