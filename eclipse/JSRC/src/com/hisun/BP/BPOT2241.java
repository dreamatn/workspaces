package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2241 {
    brParm BPTLHIS_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 30;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    BPOT2241_WS_LHIS_DETAIL WS_LHIS_DETAIL = new BPOT2241_WS_LHIS_DETAIL();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRLHIS BPRLHIS = new BPRLHIS();
    SCCMPAG SCCMPAG = new SCCMPAG();
    int WS_BR = 0;
    int WS_BEG_DT = 0;
    int WS_END_DT = 0;
    char WS_BOX_TYPE = ' ';
    SCCGWA SCCGWA;
    BPB2241_AWA_2241 BPB2241_AWA_2241;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT2241 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2241_AWA_2241>");
        BPB2241_AWA_2241 = (BPB2241_AWA_2241) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2241_AWA_2241.BR);
        CEP.TRC(SCCGWA, BPB2241_AWA_2241.BEG_DT);
        CEP.TRC(SCCGWA, BPB2241_AWA_2241.END_DT);
        CEP.TRC(SCCGWA, BPB2241_AWA_2241.BOX_TYPE);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BROWSE_RGND_RECORD();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB2241_AWA_2241.BR == ' ') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
        }
    }
    public void B020_BROWSE_RGND_RECORD() throws IOException,SQLException,Exception {
        B021_STARTBR_PROCESS();
        if (pgmRtn) return;
        B022_READNEXT_PROC();
        if (pgmRtn) return;
        B030_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        for (WS_CNT = 1; SCCGWA.COMM_AREA.DBIO_FLG != '1'; WS_CNT += 1) {
            B030_02_OUTPUT_DETAIL();
            if (pgmRtn) return;
            B022_READNEXT_PROC();
            if (pgmRtn) return;
        }
        B023_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void B021_STARTBR_PROCESS() throws IOException,SQLException,Exception {
        WS_BR = BPB2241_AWA_2241.BR;
        WS_BEG_DT = BPB2241_AWA_2241.BEG_DT;
        WS_END_DT = BPB2241_AWA_2241.END_DT;
        WS_BOX_TYPE = BPB2241_AWA_2241.BOX_TYPE;
        CEP.TRC(SCCGWA, WS_BR);
        CEP.TRC(SCCGWA, WS_BEG_DT);
        CEP.TRC(SCCGWA, WS_END_DT);
        CEP.TRC(SCCGWA, WS_BOX_TYPE);
        if (WS_BEG_DT == 0 
                && WS_END_DT == 0 
                && WS_BOX_TYPE == ' ') {
            CEP.TRC(SCCGWA, "DEV01");
            T000_STARTBR_BR1();
            if (pgmRtn) return;
        } else if (WS_BEG_DT == 0 
                && WS_END_DT == 0 
                && WS_BOX_TYPE != ' ') {
            CEP.TRC(SCCGWA, "DEV02");
            T000_STARTBR_BR2();
            if (pgmRtn) return;
        } else if (WS_BEG_DT == 0 
                && WS_END_DT != 0 
                && WS_BOX_TYPE == ' ') {
            CEP.TRC(SCCGWA, "DEV03");
            T000_STARTBR_BR3();
            if (pgmRtn) return;
        } else if (WS_BEG_DT == 0 
                && WS_END_DT != 0 
                && WS_BOX_TYPE != ' ') {
            CEP.TRC(SCCGWA, "DEV04");
            T000_STARTBR_BR4();
            if (pgmRtn) return;
        } else if (WS_BEG_DT != 0 
                && WS_END_DT == 0 
                && WS_BOX_TYPE == ' ') {
            CEP.TRC(SCCGWA, "DEV05");
            T000_STARTBR_BR5();
            if (pgmRtn) return;
        } else if (WS_BEG_DT != 0 
                && WS_END_DT == 0 
                && WS_BOX_TYPE != ' ') {
            CEP.TRC(SCCGWA, "DEV06");
            T000_STARTBR_BR6();
            if (pgmRtn) return;
        } else if (WS_BEG_DT != 0 
                && WS_END_DT != 0 
                && WS_BOX_TYPE == ' ') {
            CEP.TRC(SCCGWA, "DEV07");
            T000_STARTBR_BR7();
            if (pgmRtn) return;
        } else if (WS_BEG_DT != 0 
                && WS_END_DT != 0 
                && WS_BOX_TYPE != ' ') {
            CEP.TRC(SCCGWA, "DEV08");
            T000_STARTBR_BR8();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "DEV09");
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
        }
    }
    public void T000_STARTBR_BR1() throws IOException,SQLException,Exception {
        BPTLHIS_BR.rp = new DBParm();
        BPTLHIS_BR.rp.TableName = "BPTLHIS";
        BPTLHIS_BR.rp.where = "BR = :WS_BR";
        IBS.STARTBR(SCCGWA, BPRLHIS, this, BPTLHIS_BR);
    }
    public void T000_STARTBR_BR2() throws IOException,SQLException,Exception {
        BPTLHIS_BR.rp = new DBParm();
        BPTLHIS_BR.rp.TableName = "BPTLHIS";
        BPTLHIS_BR.rp.where = "BR = :WS_BR "
            + "AND BOX_TYPE = :WS_BOX_TYPE";
        IBS.STARTBR(SCCGWA, BPRLHIS, this, BPTLHIS_BR);
    }
    public void T000_STARTBR_BR3() throws IOException,SQLException,Exception {
        BPTLHIS_BR.rp = new DBParm();
        BPTLHIS_BR.rp.TableName = "BPTLHIS";
        BPTLHIS_BR.rp.where = "BR = :WS_BR "
            + "AND TR_DT <= :WS_END_DT";
        IBS.STARTBR(SCCGWA, BPRLHIS, this, BPTLHIS_BR);
    }
    public void T000_STARTBR_BR4() throws IOException,SQLException,Exception {
        BPTLHIS_BR.rp = new DBParm();
        BPTLHIS_BR.rp.TableName = "BPTLHIS";
        BPTLHIS_BR.rp.where = "BR = :WS_BR "
            + "AND TR_DT <= :WS_END_DT "
            + "AND BOX_TYPE = :WS_BOX_TYPE";
        IBS.STARTBR(SCCGWA, BPRLHIS, this, BPTLHIS_BR);
    }
    public void T000_STARTBR_BR5() throws IOException,SQLException,Exception {
        BPTLHIS_BR.rp = new DBParm();
        BPTLHIS_BR.rp.TableName = "BPTLHIS";
        BPTLHIS_BR.rp.where = "BR = :WS_BR "
            + "AND TR_DT >= :WS_BEG_DT";
        IBS.STARTBR(SCCGWA, BPRLHIS, this, BPTLHIS_BR);
    }
    public void T000_STARTBR_BR6() throws IOException,SQLException,Exception {
        BPTLHIS_BR.rp = new DBParm();
        BPTLHIS_BR.rp.TableName = "BPTLHIS";
        BPTLHIS_BR.rp.where = "BR = :WS_BR "
            + "AND TR_DT >= :WS_BEG_DT "
            + "AND BOX_TYPE = :WS_BOX_TYPE";
        IBS.STARTBR(SCCGWA, BPRLHIS, this, BPTLHIS_BR);
    }
    public void T000_STARTBR_BR7() throws IOException,SQLException,Exception {
        BPTLHIS_BR.rp = new DBParm();
        BPTLHIS_BR.rp.TableName = "BPTLHIS";
        BPTLHIS_BR.rp.where = "BR = :WS_BR "
            + "AND TR_DT >= :WS_BEG_DT "
            + "AND TR_DT <= :WS_END_DT";
        IBS.STARTBR(SCCGWA, BPRLHIS, this, BPTLHIS_BR);
    }
    public void T000_STARTBR_BR8() throws IOException,SQLException,Exception {
        BPTLHIS_BR.rp = new DBParm();
        BPTLHIS_BR.rp.TableName = "BPTLHIS";
        BPTLHIS_BR.rp.where = "BR = :WS_BR "
            + "AND TR_DT >= :WS_BEG_DT "
            + "AND TR_DT <= :WS_END_DT "
            + "AND BOX_TYPE = :WS_BOX_TYPE";
        IBS.STARTBR(SCCGWA, BPRLHIS, this, BPTLHIS_BR);
    }
    public void B030_01_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 110;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 5;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B030_02_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_LHIS_DETAIL);
        WS_LHIS_DETAIL.WS_LHIS_BR = BPRLHIS.KEY.BR;
        WS_LHIS_DETAIL.WS_LHIS_BR_NM = BPRLHIS.BR_NM;
        WS_LHIS_DETAIL.WS_LHIS_JRNNO = BPRLHIS.KEY.JRNNO;
        WS_LHIS_DETAIL.WS_LHIS_TR_DT = BPRLHIS.KEY.TR_DT;
        WS_LHIS_DETAIL.WS_LHIS_FROM_TLR = BPRLHIS.FROM_TLR;
        WS_LHIS_DETAIL.WS_LHIS_TO_TLR = BPRLHIS.TO_TLR;
        WS_LHIS_DETAIL.WS_LHIS_BOX_TYPE = BPRLHIS.BOX_TYPE;
        WS_LHIS_DETAIL.WS_LHIS_BOX_NO = BPRLHIS.BOX_NO;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_LHIS_DETAIL);
        SCCMPAG.DATA_LEN = 110;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B022_READNEXT_PROC() throws IOException,SQLException,Exception {
        BPTLHIS_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRLHIS, this, BPTLHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTLHIS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B023_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTLHIS_BR);
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
