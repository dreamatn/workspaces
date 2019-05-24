package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2141 {
    int JIBS_tmp_int;
    brParm BPTTHIS_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 18;
    int K_MAX_COL = 500;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_TR_ATTR = ' ';
    char WS_THIS_FLG = ' ';
    int WS_BEG_DATE = 0;
    int WS_END_DATE = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTHIS BPRTHIS = new BPRTHIS();
    BPCOTHIS BPCOTHIS = new BPCOTHIS();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB2141_AWA_2141 BPB2141_AWA_2141;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT2141 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2141_AWA_2141>");
        BPB2141_AWA_2141 = (BPB2141_AWA_2141) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2141_AWA_2141.AC_TYP);
        CEP.TRC(SCCGWA, BPB2141_AWA_2141.BEG_DATE);
        CEP.TRC(SCCGWA, BPB2141_AWA_2141.END_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            && BPCFTLRQ.INFO.TX_LVL == '0' 
            && !BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_HAS_NO_AUTH);
        }
        if (BPB2141_AWA_2141.END_DATE < BPB2141_AWA_2141.BEG_DATE) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR65);
        }
        if (BPB2141_AWA_2141.BEG_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR66);
        }
        if (BPB2141_AWA_2141.END_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR67);
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        B020_BROWSE_PROC();
        if (pgmRtn) return;
    }
    public void B020_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTHIS);
        T000_STARTBR_BPTTHIS();
        if (pgmRtn) return;
        T000_READNEXT_BPTTHIS();
        if (pgmRtn) return;
        if (WS_THIS_FLG == 'Y') {
            B060_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (WS_THIS_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, BPRTHIS.KEY.DATE);
            CEP.TRC(SCCGWA, BPB2141_AWA_2141.BEG_DATE);
            CEP.TRC(SCCGWA, BPB2141_AWA_2141.END_DATE);
            if (BPRTHIS.KEY.DATE >= BPB2141_AWA_2141.BEG_DATE 
                && BPRTHIS.KEY.DATE <= BPB2141_AWA_2141.END_DATE) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                CEP.TRC(SCCGWA, BPCPQORG.BR);
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                WS_TR_ATTR = ' ';
                WS_TR_ATTR = BPCPQORG.ATTR;
                CEP.TRC(SCCGWA, WS_TR_ATTR);
                if (WS_TR_ATTR == '2') {
                    IBS.init(SCCGWA, BPCPQORG);
                    BPCPQORG.BR = BPRTHIS.BR;
                    CEP.TRC(SCCGWA, BPCPQORG.BR);
                    S000_CALL_BPZPQORG();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCPQORG.ATTR);
                    CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
                    if (BPCPQORG.ATTR == '3') {
                        if (BPCPQORG.SUPR_BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                            B060_02_OUT_BRW_DATA();
                            if (pgmRtn) return;
                        }
                    } else {
                        if (BPRTHIS.BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                            B060_02_OUT_BRW_DATA();
                            if (pgmRtn) return;
                        }
                    }
                } else {
                    if (BPRTHIS.BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                        B060_02_OUT_BRW_DATA();
                        if (pgmRtn) return;
                    }
                }
            }
            T000_READNEXT_BPTTHIS();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTTHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B060_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 352;
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTHIS);
        BPCOTHIS.TR_BR = BPRTHIS.BR;
        BPCOTHIS.TR_TLR = BPRTHIS.TLR;
        BPCOTHIS.TR_DATE = BPRTHIS.KEY.DATE;
        BPCOTHIS.AC_NAME = BPRTHIS.AC_NAME;
        BPCOTHIS.ID_TYP = BPRTHIS.ID_TYP;
        BPCOTHIS.IDNO = BPRTHIS.IDNO;
        BPCOTHIS.AC_TYP = BPRTHIS.AC_TYP;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOTHIS);
        SCCMPAG.DATA_LEN = 352;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-INF-QUERY", BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BPTTHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "START BROWER");
        BPRTHIS.AC_TYP = BPB2141_AWA_2141.AC_TYP;
        CEP.TRC(SCCGWA, BPRTHIS.AC_TYP);
        CEP.TRC(SCCGWA, WS_BEG_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        BPTTHIS_BR.rp = new DBParm();
        BPTTHIS_BR.rp.TableName = "BPTTHIS";
        BPTTHIS_BR.rp.where = "AC_TYP = :BPRTHIS.AC_TYP";
        IBS.STARTBR(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
    }
    public void T000_READNEXT_BPTTHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        BPTTHIS_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_THIS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_THIS_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTTHIS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTHIS_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
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
