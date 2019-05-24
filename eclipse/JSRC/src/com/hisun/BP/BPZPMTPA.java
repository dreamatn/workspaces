package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPMTPA {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTMTPA_BR = new brParm();
    boolean pgmRtn = false;
    int K_MAX_ROW = 18;
    int K_MAX_COL = 500;
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPRMTPA BPRMTPA = new BPRMTPA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCOMTPA BPCOMTPA = new BPCOMTPA();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPMTPA BPCPMTPA;
    public void MP(SCCGWA SCCGWA, BPCPMTPA BPCPMTPA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPMTPA = BPCPMTPA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPMTPA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCPMTPA.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPMTPA.DATA_INFO.BR);
        CEP.TRC(SCCGWA, BPCPMTPA.DATA_INFO.APP_FLG);
        CEP.TRC(SCCGWA, BPCPMTPA.DATA_INFO.MT_FLG);
        BPCPQORG.BR = BPCPMTPA.DATA_INFO.BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND);
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPMTPA.INFO.FUNC);
        if (BPCPMTPA.INFO.FUNC == 'B') {
            B020_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR, BPCPMTPA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRMTPA);
        B030_TRANS_DATA();
        if (pgmRtn) return;
        if (BPCPMTPA.DATA_INFO.APP_FLG == ' ' 
            && BPCPMTPA.DATA_INFO.MT_FLG == ' ') {
            T000_STARTBR_BPTMTPA_NONE();
            if (pgmRtn) return;
        }
        if (BPCPMTPA.DATA_INFO.APP_FLG == ' ' 
            && BPCPMTPA.DATA_INFO.MT_FLG != ' ') {
            T000_STARTBR_BPTMTPA_MT();
            if (pgmRtn) return;
        }
        if (BPCPMTPA.DATA_INFO.APP_FLG != ' ' 
            && BPCPMTPA.DATA_INFO.MT_FLG == ' ') {
            T000_STARTBR_BPTMTPA_APP();
            if (pgmRtn) return;
        }
        if (BPCPMTPA.DATA_INFO.APP_FLG != ' ' 
            && BPCPMTPA.DATA_INFO.MT_FLG != ' ') {
            T000_STARTBR_BPTMTPA_ALL();
            if (pgmRtn) return;
        }
        T000_READNEXT_BPTMTPA();
        if (pgmRtn) return;
        if (BPCPMTPA.RETURN_INFO == 'F') {
            B060_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (BPCPMTPA.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B060_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_BPTMTPA();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTMTPA();
        if (pgmRtn) return;
    }
    public void B030_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRMTPA);
        BPRMTPA.KEY.MT_BR = BPCPMTPA.DATA_INFO.BR;
        BPRMTPA.KEY.APP_FLG = BPCPMTPA.DATA_INFO.APP_FLG;
        BPRMTPA.MT_FLG = BPCPMTPA.DATA_INFO.MT_FLG;
    }
    public void B060_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 36;
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOMTPA);
        BPCOMTPA.BR = BPRMTPA.KEY.MT_BR;
        BPCOMTPA.APP_FLG = BPRMTPA.KEY.APP_FLG;
        BPCOMTPA.MT_FLG = BPRMTPA.MT_FLG;
        BPCOMTPA.TLR = BPRMTPA.MT_TLR;
        BPCOMTPA.DT = BPRMTPA.MT_DT;
        BPCOMTPA.STAT_TM = BPRMTPA.STAT_TM;
        BPCOMTPA.END_TM = BPRMTPA.END_TM;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOMTPA);
        SCCMPAG.DATA_LEN = 36;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTMTPA_NONE() throws IOException,SQLException,Exception {
        BPTMTPA_BR.rp = new DBParm();
        BPTMTPA_BR.rp.TableName = "BPTMTPA";
        BPTMTPA_BR.rp.where = "MT_BR = :BPRMTPA.KEY.MT_BR";
        IBS.STARTBR(SCCGWA, BPRMTPA, this, BPTMTPA_BR);
    }
    public void T000_STARTBR_BPTMTPA_MT() throws IOException,SQLException,Exception {
        BPTMTPA_BR.rp = new DBParm();
        BPTMTPA_BR.rp.TableName = "BPTMTPA";
        BPTMTPA_BR.rp.where = "MT_BR = :BPRMTPA.KEY.MT_BR "
            + "AND MT_FLG = :BPRMTPA.MT_FLG";
        IBS.STARTBR(SCCGWA, BPRMTPA, this, BPTMTPA_BR);
    }
    public void T000_STARTBR_BPTMTPA_APP() throws IOException,SQLException,Exception {
        BPTMTPA_BR.rp = new DBParm();
        BPTMTPA_BR.rp.TableName = "BPTMTPA";
        BPTMTPA_BR.rp.where = "MT_BR = :BPRMTPA.KEY.MT_BR "
            + "AND APP_FLG = :BPRMTPA.KEY.APP_FLG";
        IBS.STARTBR(SCCGWA, BPRMTPA, this, BPTMTPA_BR);
    }
    public void T000_STARTBR_BPTMTPA_ALL() throws IOException,SQLException,Exception {
        BPTMTPA_BR.rp = new DBParm();
        BPTMTPA_BR.rp.TableName = "BPTMTPA";
        BPTMTPA_BR.rp.where = "MT_BR = :BPRMTPA.KEY.MT_BR "
            + "AND APP_FLG = :BPRMTPA.KEY.APP_FLG "
            + "AND MT_FLG = :BPRMTPA.MT_FLG";
        IBS.STARTBR(SCCGWA, BPRMTPA, this, BPTMTPA_BR);
    }
    public void T000_READNEXT_BPTMTPA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "NEXT BROWER");
        IBS.READNEXT(SCCGWA, BPRMTPA, this, BPTMTPA_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCPMTPA.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCPMTPA.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCPMTPA.RC);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTMTPA";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTMTPA() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTMTPA_BR);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC);
        }
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
        if (BPCPMTPA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPMTPA = ");
            CEP.TRC(SCCGWA, BPCPMTPA);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
