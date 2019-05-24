package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2071 {
    DBParm BPTINVT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP271";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    BPOT2071_WS_OUTPUT_DETAIL WS_OUTPUT_DETAIL = new BPOT2071_WS_OUTPUT_DETAIL();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRINVT BPRINVT = new BPRINVT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCGWA SCCGWA;
    BPB2070_AWA_2070 BPB2070_AWA_2070;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT2071 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2070_AWA_2070>");
        BPB2070_AWA_2070 = (BPB2070_AWA_2070) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_QUERY_INVT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2070_AWA_2070.DT);
        CEP.TRC(SCCGWA, BPB2070_AWA_2070.JRNNO);
    }
    public void B020_QUERY_INVT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINVT);
        BPRINVT.KEY.DATE = BPB2070_AWA_2070.DT;
        BPRINVT.KEY.JRNNO = BPB2070_AWA_2070.JRNNO;
        T000_READ_BPTINVT();
        if (pgmRtn) return;
        WS_OUTPUT_DETAIL.WS_TR_DT = BPRINVT.KEY.DATE;
        WS_OUTPUT_DETAIL.WS_JRNNO = BPRINVT.KEY.JRNNO;
        for (WS_CNT = 1; WS_CNT <= 200; WS_CNT += 1) {
            WS_OUTPUT_DETAIL.WS_DETAIL[WS_CNT-1].WS_MACH_BV_CODE = BPRINVT.REDEFINES28.FILLER1.REC_DATA[WS_CNT-1].MACH_BV_CODE;
            WS_OUTPUT_DETAIL.WS_DETAIL[WS_CNT-1].WS_MACH_NUM = BPRINVT.REDEFINES28.FILLER1.REC_DATA[WS_CNT-1].MACH_NUM;
            WS_OUTPUT_DETAIL.WS_DETAIL[WS_CNT-1].WS_ACTU_NUM = BPRINVT.REDEFINES28.FILLER1.REC_DATA[WS_CNT-1].ACTU_NUM;
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, WS_OUTPUT_DETAIL.WS_DETAIL[WS_CNT-1].WS_MACH_BV_CODE);
            CEP.TRC(SCCGWA, WS_OUTPUT_DETAIL.WS_DETAIL[WS_CNT-1].WS_MACH_NUM);
            CEP.TRC(SCCGWA, WS_OUTPUT_DETAIL.WS_DETAIL[WS_CNT-1].WS_ACTU_NUM);
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT_DETAIL;
        SCCFMT.DATA_LEN = 4820;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_BPTINVT() throws IOException,SQLException,Exception {
        BPTINVT_RD = new DBParm();
        BPTINVT_RD.TableName = "BPTINVT";
        BPTINVT_RD.where = "'DATE' = :BPRINVT.KEY.DATE "
            + "AND JRNNO = :BPRINVT.KEY.JRNNO";
        IBS.READ(SCCGWA, BPRINVT, this, BPTINVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR152);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTINVT  ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
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
