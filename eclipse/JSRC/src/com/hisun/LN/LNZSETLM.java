package com.hisun.LN;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSETLM {
    int JIBS_tmp_int;
    brParm LNTSETL_BR = new brParm();
    DBParm LNTSETL_RD;
    DBParm LNTCONT_RD;
    DBParm LNTICTL_RD;
    DBParm LNTAPRD_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    String WS_CONT = " ";
    double WS_TOT_BAL = 0;
    int WS_CNT_LIN = 0;
    LNZSETLM_WS_DATA WS_DATA = new LNZSETLM_WS_DATA();
    char WS_REC_SETL_FLG = ' ';
    char WS_REC_PAIP_FLG = ' ';
    char WS_REC_ICTL_FLG = ' ';
    char WS_REC_APRD_FLG = ' ';
    char WS_REC_CONT_FLG = ' ';
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCICIQ LNCICIQ = new LNCICIQ();
    LNRSETL LNRSETL = new LNRSETL();
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNRCONT LNRCONT = new LNRCONT();
    LNRICTL LNRICTL = new LNRICTL();
    LNRAPRD LNRAPRD = new LNRAPRD();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNCRSETL LNCRSETL = new LNCRSETL();
    LNCRPAIP LNCRPAIP = new LNCRPAIP();
    LNCSPDQ LNCSPDQ = new LNCSPDQ();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNCBALLM LNCBALLM = new LNCBALLM();
    int WS_CNT2 = 0;
    int WS_VAL_DATE = 0;
    int WS_START_NUM = 0;
    short WS_QG_TERM = 0;
    int WS_DT = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCAWAC SCCAWAC;
    LNCSETLM LNCSETLM;
    public void MP(SCCGWA SCCGWA, LNCSETLM LNCSETLM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSETLM = LNCSETLM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSETLM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        LNCSETLM.RC.RC_APP = "LN";
        LNCSETLM.RC.RC_RTNCODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCSETLM.FUNC == 'I') {
            B010_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCSETLM.FUNC == 'M') {
            B020_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCSETLM.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        LNCSETLM.CON_FLG = 'Y';
        LNRSETL.AC = LNCSETLM.DD_AC;
        LNRSETL.KEY.CCY = LNCSETLM.CCY;
        LNRSETL.CCY_TYP = LNCSETLM.CCY_TYP;
        T000_STARTBR_LNTSETL_ATTR();
        if (pgmRtn) return;
        T000_READNEXT_LNTSETL_ATTR();
        if (pgmRtn) return;
        while (WS_REC_SETL_FLG != 'N' 
            && LNCSETLM.CON_FLG != 'N') {
            T000_READ_LNTCONT_ATTR();
            if (pgmRtn) return;
            if (WS_REC_CONT_FLG == 'Y') {
                T000_READ_LNTICTL_ATTR();
                if (pgmRtn) return;
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                    || LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
                } else {
                    LNCSETLM.CON_FLG = 'N';
                }
            }
            T000_READNEXT_LNTSETL_ATTR();
            if (pgmRtn) return;
        }
        T000_ENDBR_LNTSETL();
        if (pgmRtn) return;
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.AC = LNCSETLM.DD_AC;
        T000_STARTBR_LNTSETL_ATTR();
        if (pgmRtn) return;
        T000_READNEXT_LNTSETL_ATTR();
        if (pgmRtn) return;
        while (WS_REC_SETL_FLG != 'N') {
            T000_READ_LNTCONT_ATTR();
            if (pgmRtn) return;
            if (WS_REC_CONT_FLG == 'Y') {
                T000_READ_LNTICTL_ATTR();
                if (pgmRtn) return;
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
                JIBS_tmp_int = LNRICTL.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
                if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
                    || LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1")) {
                } else {
                    T000_READ_UPDATE_PROC();
                    if (pgmRtn) return;
                    LNRSETL.AC = LNCSETLM.DD_AC_NEW;
                    T000_REWRITE_REC_PROC();
                    if (pgmRtn) return;
                }
            }
            T000_READNEXT_LNTSETL_ATTR();
            if (pgmRtn) return;
        }
        T000_ENDBR_LNTSETL();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_LNTSETL_ATTR() throws IOException,SQLException,Exception {
        LNTSETL_BR.rp = new DBParm();
        LNTSETL_BR.rp.TableName = "LNTSETL";
        LNTSETL_BR.rp.col = "CONTRACT_NO";
        LNTSETL_BR.rp.where = "AC = :LNRSETL.AC "
            + "AND SETTLE_TYPE = '2' "
            + "AND ( AC_TYP = '01' "
            + "OR AC_TYP = '05' ) "
            + "AND CCY = :LNRSETL.KEY.CCY "
            + "AND CCY_TYP = :LNRSETL.CCY_TYP";
        LNTSETL_BR.rp.order = "CONTRACT_NO";
        IBS.STARTBR(SCCGWA, LNRSETL, this, LNTSETL_BR);
    }
    public void T000_READNEXT_LNTSETL_ATTR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRSETL, this, LNTSETL_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FOUND");
            WS_REC_SETL_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FOUND");
            WS_REC_SETL_FLG = 'N';
        }
    }
    public void T000_ENDBR_LNTSETL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTSETL_BR);
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.upd = true;
        IBS.READ(SCCGWA, LNRSETL, LNTSETL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_SETL_NOTFND, LNCRSETL.RC);
            LNCRSETL.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        LNRSETL.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSETL.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        IBS.REWRITE(SCCGWA, LNRSETL, LNTSETL_RD);
    }
    public void T000_READ_LNTCONT_ATTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNRSETL.KEY.CONTRACT_NO;
        T000_READ_LNTCONT();
        if (pgmRtn) return;
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FOUND");
            WS_REC_CONT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FOUND");
            WS_REC_CONT_FLG = 'N';
        }
    }
    public void T000_READ_LNTICTL_ATTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNRSETL.KEY.CONTRACT_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        T000_READ_LNTICTL();
        if (pgmRtn) return;
    }
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FOUND");
            WS_REC_ICTL_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FOUND");
            WS_REC_ICTL_FLG = 'N';
        }
    }
    public void T000_READ_LNTAPRD_ATTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAPRD);
        LNRAPRD.KEY.CONTRACT_NO = LNRSETL.KEY.CONTRACT_NO;
        T000_READ_LNTAPRD();
        if (pgmRtn) return;
    }
    public void T000_READ_LNTAPRD() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        IBS.READ(SCCGWA, LNRAPRD, LNTAPRD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FOUND");
            WS_REC_APRD_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FOUND");
            WS_REC_APRD_FLG = 'N';
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
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSETLM.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSETLM=");
            CEP.TRC(SCCGWA, LNCSETLM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
